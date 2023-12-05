package almanac

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RangeTest {
    @Test
    fun `given 50 98 2 when parse() then Range returned`() {
        val input = "50 98 2"
        val expected = AlmanacMap.Range(
            destinationStartIndex = 50.toBigInteger(),
            sourceStartIndex = 98.toBigInteger(),
            length = 2.toBigInteger()
        )

        val actual = AlmanacMap.Range.parse(input)

        assertEquals(expected, actual)
    }
}