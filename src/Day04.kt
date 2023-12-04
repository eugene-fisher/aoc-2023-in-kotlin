import scratch.ScratchCardParser
import scratch.checkValue
import scratch.getCopies

fun main() {
    val cardParser = ScratchCardParser()
    fun part1(input: List<String>): Int {
        return input.sumOf { cardParser.parse(it).checkValue() }
    }

    fun part2(input: List<String>): Int {
        val originalCards = input.map { cardParser.parse(it) }
        return originalCards.getCopies(haveCards = originalCards).size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
