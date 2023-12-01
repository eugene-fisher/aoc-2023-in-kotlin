package calibration

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SpelledDigitCalibrationCalculatorTest {
    private val sut = SpelledDigitCalibrationCalculator(emptyList())

    @Test
    fun `given two1nine when findLineValue() then 29 returned`() {
        val input = "two1nine"
        val expected = 29

        val actual = sut.findLineValue(input)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `given eightwothree when findLineValue() then 83 returned`() {
        val input = "eightwothree"
        val expected = 83

        val actual = sut.findLineValue(input)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `given abcone2threexyz when findLineValue() then 13 returned`() {
        val input = "abcone2threexyz"
        val expected = 13

        val actual = sut.findLineValue(input)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `given xtwone3four when findLineValue() then 24 returned`() {
        val input = "xtwone3four"
        val expected = 24

        val actual = sut.findLineValue(input)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `given 4nineeightseven2 when findLineValue() then 42 returned`() {
        val input = "4nineeightseven2"
        val expected = 42

        val actual = sut.findLineValue(input)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `given zoneight234 when findLineValue() then 14 returned`() {
        val input = "zoneight234"
        val expected = 14

        val actual = sut.findLineValue(input)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `given 7pqrstsixteen when findLineValue() then 76 returned`() {
        val input = "7pqrstsixteen"
        val expected = 76

        val actual = sut.findLineValue(input)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `given nodigits when findLineValue() then error`() {
        val input = "nodigits"

        org.junit.jupiter.api.assertThrows<NoSuchElementException> {
            sut.findLineValue(input)
        }
    }
}