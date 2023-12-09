import extrapolation.Extrapolator

fun main() {
    fun part1(input: List<String>): Int {
        val extrapolator = Extrapolator()
        return input.sumOf { line -> extrapolator.nextValue(sequence = line.split(' ').map { it.toInt() }) }
    }

    fun part2(input: List<String>): Int {
        val extrapolator = Extrapolator()
        return input.sumOf { line -> extrapolator.previousValue(sequence = line.split(' ').map { it.toInt() }) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part2(testInput) == 2)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
