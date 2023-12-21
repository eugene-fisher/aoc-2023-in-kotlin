package math

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MathKtTest {

    @Test
    fun `given 2, 6 when lcm() then 6 returned`() {
        val expected = 6L

        val actual = lcm(2, 6)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 4, 6 when lcm() then 12 returned`() {
        val expected = 12L

        val actual = lcm(4, 6)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 2, 6, 9, 8 when lcm() then 72 returned`() {
        val input = longArrayOf(2, 6, 9, 8)
        val expected = 72L

        val actual = lcm(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given 4019, 3923, 3919, 3821 when lcm() then 236095992539963 returned`() {
        val input = longArrayOf(4019, 3923, 3919, 3821)
        val expected = 236095992539963L

        val actual = lcm(input)

        assertEquals(expected, actual)
    }
}