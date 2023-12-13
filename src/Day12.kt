import hotsprings.ConditionRecord
import hotsprings.HotSpringAnalyzer

fun main() {
    fun part1(input: List<String>): Int {
        val analyzer = HotSpringAnalyzer()
        return input.sumOf { analyzer.analyzePossibleArrangements(ConditionRecord.parse(it)) }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 21)

    val input = readInput("Day12")
    part1(input).println()
    part2(input).println()
}
