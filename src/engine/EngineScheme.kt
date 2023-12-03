package engine

class EngineScheme(schematics: List<String>) : Iterable<EngineScheme.Element> {
    private val width = schematics.first().length
    private val height = schematics.size

    private val elements = Array<Element>(width * height) { Element.Nothing }

    init {
        for (y in 0..<width) {
            val line = schematics[y]
            var parsing: Element = Element.Nothing
            for (x in 0..<height) {
                parsing = Element.from(char = line[x], previous = parsing)
                set(x, y, parsing)
            }
        }
    }

    override fun iterator(): Iterator<Element> = elements.iterator()

    fun get(x: Int, y: Int) = elements[index(x, y)]

    private fun set(x: Int, y: Int, element: Element) {
        elements[index(x, y)] = element
    }

    fun adjacentPositions(index: Int): List<Position> {
        val x = index % width
        val y = index / height
        return listOfNotNull(
            Position(x = x - 1, y = y - 1).takeIf { it.isValid },
            Position(x = x, y = y - 1).takeIf { it.isValid },
            Position(x = x + 1, y = y - 1).takeIf { it.isValid },
            Position(x = x - 1, y = y).takeIf { it.isValid },
            Position(x = x, y = y).takeIf { it.isValid },
            Position(x = x + 1, y = y).takeIf { it.isValid },
            Position(x = x - 1, y = y + 1).takeIf { it.isValid },
            Position(x = x, y= y + 1).takeIf { it.isValid },
            Position(x = x + 1, y= y + 1).takeIf { it.isValid },
        )
    }

    inline fun <reified T : Element> adjacent(index: Int): List<T> {
        return adjacentPositions(index)
            .mapNotNull { (x, y) -> get(x, y) as? T }
            .distinct()
    }

    private fun index(x: Int, y: Int): Int = x + y * width

    private val Position.isValid get() = x in 0..<width && y in 0..height

    sealed class Element {

        data object Nothing : Element()

        data object Symbol : Element()

        class Part(var number: Int) : Element()

        companion object {
            fun from(char: Char, previous: Element): Element = when {
                char.isSymbol() -> Symbol
                char.isDigit() -> {
                    if (previous is Part) {
                        previous.apply { number = number.addLowerDigit(char.digitToInt()) }
                    } else {
                        Part(number = char.digitToInt())
                    }
                }
                else -> Nothing
            }

            private fun Int.addLowerDigit(digit: Int) = this * 10 + digit

            private fun Char.isSymbol() = !isLetterOrDigit() && this != '.'
        }
    }

    data class Position(val x: Int, val y: Int)
}