import circuits.Circuit
import circuits.CircuitSignal
import math.lcm

fun main() {
    fun part1(input: List<String>, logs: Boolean = false): Long {
        val circuit = Circuit.parse(input)

        var highs = 0L
        var lows = 0L

        circuit.elements.forEach {
            it.instrumentation = { signal, outputs ->
                outputs.forEach { output ->
                    if (logs) println("$name -${signal?.name}->$output")
                    when (signal) {
                        CircuitSignal.HIGH -> highs++
                        CircuitSignal.LOW -> lows++
                        null -> Unit
                    }
                }
            }
        }

        repeat(1000) {
            circuit.pushButton()
        }
        return lows * highs
    }

    fun part2(input: List<String>): Long {
        val circuit = Circuit.parse(input)

        val targetElement = circuit["rx"]
        val targetSupplier = targetElement.inputs.keys.single()

        var iteration = 0
        val loopsMap = mutableMapOf<String, MutableList<Int>>()

        targetSupplier.instrumentation = { _, _ ->
            inputs.forEach { (connection, signal) ->
                if (signal == CircuitSignal.HIGH) {
                    loopsMap.getOrPut(connection.name) { mutableListOf() }.add(iteration)
                }
            }
        }

        repeat(10000) {
            iteration++
            circuit.pushButton()
        }

        return lcm(loopsMap.values.map { it.first().toLong() }.toLongArray())
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day20_test")
    check(part1(testInput, logs = true) == 11687500L)

    val input = readInput("Day20")
    part1(input).println()
    part2(input).println()
}