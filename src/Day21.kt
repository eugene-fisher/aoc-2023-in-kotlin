import steps.StepsMap

fun main() {
    fun part1(input: List<String>): Int {
        val map = StepsMap(width = input.first().length, height = input.size)
        var startX = -1
        var startY = -1
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, tile ->
                when (tile) {
                    '#' -> map.putRock(x, y)
                    'S' -> {startX = x; startY = y}
                    '.' -> Unit
                    else -> throw IllegalStateException("Unknown tile $tile")
                }
            }
        }
        val potential = map.startFrom(StepsMap.Position(startX, startY), steps = 64)
        return potential.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day21_test")
    check(part1(testInput) == 42)

    val input = readInput("Day21")
    part1(input).println()
    part2(testInput).println()
}
