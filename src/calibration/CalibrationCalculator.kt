package calibration

abstract class CalibrationCalculator(
    private val input: List<String>
) {

    fun calculateTotalValue(): Int = input.sumOf { findLineValue(it) }

    abstract fun findLineValue(line: String): Int
}

