package engine

class EngineAnalyzer(schematics: List<String>) {

    private val schema = EngineScheme(schematics)

    val adjacentParts: List<Int>
        get() {
            return schema.flatMapIndexed { index: Int, element: EngineScheme.Element ->
                if (element is EngineScheme.Element.Symbol) {
                    schema.adjacent<EngineScheme.Element.Part>(index)
                } else emptyList()
            }.map { it.number }
        }

    val gears: List<Pair<Int, Int>>
        get() {
            return schema.mapIndexedNotNull { index: Int, element: EngineScheme.Element ->
                if (element is EngineScheme.Element.Symbol) {
                    val gear = schema.adjacent<EngineScheme.Element.Part>(index).takeIf { it.size == 2 }
                    gear?.let { it.first().number to it.last().number }
                } else null
            }
        }
}
