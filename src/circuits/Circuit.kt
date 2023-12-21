package circuits

class Circuit {
    private val outputsMap = mutableMapOf<CircuitElement, MutableList<CircuitElement>>()

    val elements: List<CircuitElement> get() = outputsMap.flatMap { (element, outputs) -> outputs + element }

    fun pushButton() {
        var toTrigger: List<CircuitElement> = listOf(get(CircuitElement.Button.NAME))
        while (toTrigger.isNotEmpty()) {
            val next = toTrigger.flatMap { element ->
                val signal = element.execute(outputsMap[element])
                if (signal != null) {
                    outputsMap[element]?.onEach {
                        it.inputs[element] = signal
                    } ?: emptyList()
                } else emptyList()
            }
            toTrigger = next
        }
    }

    operator fun get(element: String): CircuitElement = elements.first { it.name == element }

    fun connect(element: CircuitElement, to: CircuitElement) {
        outputsMap.getOrPut(element) { mutableListOf() }.add(to)
        to.inputs[element] = null
    }

    companion object {
        fun parse(input: List<String>): Circuit {
            val elements = mutableMapOf<String, CircuitElement>()
            val connections = mutableMapOf<String, List<String>>()

            input.forEach {
                val (element, elementConnections) = parseElement(it)
                elements[element.name] = element
                connections[element.name] = elementConnections
            }

            val button = CircuitElement.Button()
            return Circuit().apply {
                connect(
                    element = button,
                    to = requireNotNull(elements[CircuitElement.Broadcaster.NAME])
                )
                connections.forEach { (elementName, connectionNames) ->
                    connectionNames.forEach { connectionName ->
                        connect(
                            element = requireNotNull(elements[elementName]),
                            to = elements[connectionName] ?: CircuitElement.Output(name = connectionName)
                        )
                    }
                }
            }
        }

        private fun parseElement(line: String): Pair<CircuitElement, List<String>> {
            val (element, connections) = line.split(" -> ")
            val circuitElement = when {
                element.startsWith('%') -> CircuitElement.FlipFlop(name = element.substring(1))
                element.startsWith('&') -> CircuitElement.Conjunction(name = element.substring(1))
                element == "broadcaster" -> CircuitElement.Broadcaster()
                else -> throw IllegalArgumentException("Unknown element $element")
            }
            return circuitElement to connections.split(", ")
        }
    }
}