import calibration.DigitCalibrationCalculator
import calibration.SpelledDigitCalibrationCalculator

fun main() {
    fun part1(input: List<String>): Int {
        val calculator = DigitCalibrationCalculator(input)
        return calculator.calculateTotalValue()
    }

    fun part2(input: List<String>): Int {
        val calculator = SpelledDigitCalibrationCalculator(input)
        return calculator.calculateTotalValue()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
