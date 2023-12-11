import space.Space

fun main() {
    fun part1(input: List<String>): Long {
        val space = Space(map = input)
        space.expand()
        return space.calculateShortestPathBetweenGalaxies()
    }

    fun part2(input: List<String>): Long {
        val space = Space(map = input)
        space.expand(power = 1_000_000)
        return space.calculateShortestPathBetweenGalaxies()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 374L)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
