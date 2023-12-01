package calibration

class SpelledDigitCalibrationCalculator(input: List<String>) : CalibrationCalculator(input) {

    private val spelledDigits = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    private val digits = List(10) { it }

    override fun findLineValue(line: String): Int {
        val digitFindings = digits.mapNotNull { digit -> line.findIndex(digit, digit.toString()) }
        val spelledFindings = spelledDigits.entries.mapNotNull { (representation, digit) ->
            line.findIndex(digit, representation)
        }

        val firstDigitEntry = digitFindings.minByOrNull { it.index }
        val firstSpelledEntry = spelledFindings.minByOrNull { it.index }
        val firstDigit = listOfNotNull(firstDigitEntry, firstSpelledEntry).min().digit

        val lastDigitEntry = digitFindings.maxByOrNull { it.index }
        val lastSpelledEntry = spelledFindings.maxByOrNull { it.index }
        val lastDigit = listOfNotNull(lastDigitEntry, lastSpelledEntry).max().digit

        return firstDigit * 10 + lastDigit
    }

    private fun String.findIndex(digit: Int, representation: String): FoundIndex? {
        return indexOf(representation)
            .takeIf { it >= 0 }
            ?.let { FoundIndex(index = it, digit = digit) }
    }

    private class FoundIndex(val index: Int, val digit: Int) : Comparable<FoundIndex> {
        override fun compareTo(other: FoundIndex): Int = index.compareTo(other.index)
    }
}