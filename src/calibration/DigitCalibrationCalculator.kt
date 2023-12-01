package calibration

class DigitCalibrationCalculator(input: List<String>) : CalibrationCalculator(input) {

    override fun findLineValue(line: String): Int {
        val firstDigit = line.first { it.isDigit() }.digitToInt()
        val lastDigit = line.last { it.isDigit() }.digitToInt()
        return firstDigit * 10 + lastDigit
    }
}