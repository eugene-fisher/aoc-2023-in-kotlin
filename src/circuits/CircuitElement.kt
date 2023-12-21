package circuits

import circuits.CircuitSignal.*

abstract class CircuitElement(val name: String) {

    val inputs = mutableMapOf<CircuitElement, CircuitSignal?>()

    var instrumentation: (CircuitElement.(CircuitSignal?, outputs: List<String>) -> Unit)? = null

    fun execute(outputs: MutableList<CircuitElement>?): CircuitSignal? {
        return trigger().also { signal ->
            instrumentation?.invoke(this, signal, outputs?.map { it.name } ?: emptyList())
            inputs.keys.forEach { inputs[it] = null }
        }
    }

    abstract fun trigger(): CircuitSignal?

    class Button : CircuitElement(NAME) {
        override fun trigger() = LOW

        companion object {
            const val NAME = "button"
        }
    }

    class Broadcaster : CircuitElement(NAME) {
        override fun trigger() = inputs.values.single()

        companion object {
            const val NAME = "broadcaster"
        }
    }

    class FlipFlop(name: String) : CircuitElement(name) {
        private var turnedOn = false

        override fun trigger(): CircuitSignal? {
            return if (inputs.any { it.value == LOW }) flip() else null
        }

        private fun flip(): CircuitSignal {
            turnedOn = !turnedOn
            return if (turnedOn) HIGH else LOW
        }
    }

    class Conjunction(name: String) : CircuitElement(name) {
        private val lastState = mutableMapOf<CircuitElement, CircuitSignal>()

        override fun trigger(): CircuitSignal {
            if (lastState.isEmpty()) {
                inputs.mapValuesTo(lastState) { it.value ?: LOW }
            } else {
                inputs.mapNotNullValuesTo(lastState)
            }
            return if (lastState.all { it.value == HIGH }) LOW else HIGH
        }

        private fun <K, V> Map<K, V?>.mapNotNullValuesTo(destination: MutableMap<K, V>) {
            return forEach { (connection, signal) ->
                if (signal != null) {
                    destination[connection] = signal
                }
            }
        }
    }

    class Output(name: String) : CircuitElement(name) {
        override fun trigger() = null
    }
}