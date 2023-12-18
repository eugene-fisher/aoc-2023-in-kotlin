import trenches.Trench

fun main() {
    fun part1(input: List<String>): Int {
        val trench = Trench()
        input.forEach {
            val (direction, steps) = it.split(' ')
            trench.dig(
                direction = when (direction) {
                    "U" -> Trench.Direction.UP
                    "D" -> Trench.Direction.DOWN
                    "L" -> Trench.Direction.LEFT
                    "R" -> Trench.Direction.RIGHT
                    else -> throw IllegalArgumentException("Unknown direction $direction")
                },
                steps = steps.toInt(),
            )
        }
        return trench.area.toInt()
    }

    fun part2(input: List<String>): Long {
        val trench = Trench()
        input.forEach {
            val encoded = it.substring(it.indexOf('#') + 1, it.lastIndexOf(')'))
            trench.dig(
                direction = when (encoded.last()) {
                    '0' -> Trench.Direction.RIGHT
                    '1' -> Trench.Direction.DOWN
                    '2' -> Trench.Direction.LEFT
                    '3' -> Trench.Direction.UP
                    else -> throw IllegalArgumentException("Unknown direction ${encoded.last()}")
                },
                steps = encoded.take(5).toInt(radix = 16),
            )
        }
        return trench.area.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day18_test")
    check(part1(testInput) == 62)
    check(part2(testInput) == 952408144115)

    val input = readInput("Day18")
    part1(input).println()
    part2(input).println()
}
