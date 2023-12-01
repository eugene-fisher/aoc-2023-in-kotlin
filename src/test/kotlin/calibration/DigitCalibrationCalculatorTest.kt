package calibration

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DigitCalibrationCalculatorTest {
    private val sut = DigitCalibrationCalculator(emptyList())

    @Test
    fun `given 1abc2 when findLineValue() then 12 returned`() {
        val input = "1abc2"
        val expected = 12

        val actual = sut.findLineValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given pqr3stu8vwx when findLineValue() then 38 returned`() {
        val input = "pqr3stu8vwx"
        val expected = 38

        val actual = sut.findLineValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given a1b2c3d4e5f when findLineValue() then 15 returned`() {
        val input = "a1b2c3d4e5f"
        val expected = 15

        val actual = sut.findLineValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given treb7uchet when findLineValue() then 77 returned`() {
        val input = "treb7uchet"
        val expected = 77

        val actual = sut.findLineValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given nodigits when findLineValue() then error`() {
        val input = "nodigits"

        org.junit.jupiter.api.assertThrows<NoSuchElementException> {
            sut.findLineValue(input)
        }
    }
}