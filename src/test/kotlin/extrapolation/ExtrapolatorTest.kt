package extrapolation

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ExtrapolatorTest {

    private val sut = Extrapolator()

    @Test
    fun `given 0, 3, 6, 9, 12, 15 when calculateDiff() 3, 3, 3, 3, 3 returned`() {
        val input = listOf(0, 3, 6, 9, 12, 15)
        val expected = listOf(3, 3, 3, 3, 3)

        val actual = sut.calculateDiff(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 3, 3, 3, 3, 3 when calculateDiff() 0, 0, 0, 0 returned`() {
        val input = listOf(3, 3, 3, 3, 3)
        val expected = listOf(0, 0, 0, 0)

        val actual = sut.calculateDiff(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 0, 3, 6, 9, 12, 15 when nextValue() 18 returned`() {
        val input = listOf(0, 3, 6, 9, 12, 15)
        val expected = 18

        val actual = sut.nextValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 1, 3, 6, 10, 15, 21 when nextValue() 28 returned`() {
        val input = listOf(1, 3, 6, 10, 15, 21)
        val expected = 28

        val actual = sut.nextValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 10, 13, 16, 21, 30, 45 when nextValue() 68 returned`() {
        val input = listOf(10, 13, 16, 21, 30, 45)
        val expected = 68

        val actual = sut.nextValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 0, 3, 6, 9, 12, 15 when previousValue() -3 returned`() {
        val input = listOf(0, 3, 6, 9, 12, 15)
        val expected = -3

        val actual = sut.previousValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 1, 3, 6, 10, 15, 21 when previousValue() 0 returned`() {
        val input = listOf(1, 3, 6, 10, 15, 21)
        val expected = 0

        val actual = sut.previousValue(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 10, 13, 16, 21, 30, 45 when previousValue() 5 returned`() {
        val input = listOf(10, 13, 16, 21, 30, 45)
        val expected = 5

        val actual = sut.previousValue(input)

        assertEquals(expected, actual)
    }
}