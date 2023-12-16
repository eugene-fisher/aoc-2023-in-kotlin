import rocks.RocksPlatform
import rocks.RocksRoller

fun main() {
    fun part1(input: List<String>): Int {
        val platform = RocksPlatform.parse(input)
        platform.tiltNorth()
        return platform.calculateLoadNorth()
    }

    fun part2(input: List<String>): Int {
        val platform = RocksPlatform.parse(input)
        val roller = RocksRoller(platform)
        return roller.runCycles(1000000000)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    check(part1(testInput) == 136)
    check(part2(testInput) == 64)

    val input = readInput("Day14")
    part1(input).println()
    part2(input).println()
}
