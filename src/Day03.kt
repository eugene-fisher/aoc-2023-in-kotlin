import engine.EngineAnalyzer

fun main() {
    fun part1(input: List<String>): Int {
        val analyzer = EngineAnalyzer(schematics = input)
        return analyzer.adjacentParts.sum()
    }

    fun part2(input: List<String>): Int {
        val analyzer = EngineAnalyzer(schematics = input)
        return analyzer.gears.sumOf { (a, b) -> a * b }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
