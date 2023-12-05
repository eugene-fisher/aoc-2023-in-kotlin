package almanac

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AlmanacMapTest {

    @Test
    fun `given range(50, 98, 2) when get(98) then 50 returned`() {
        val input = AlmanacMap.Range(50.toBigInteger(), 98.toBigInteger(), 2.toBigInteger())
        val expected = 50.toBigInteger()

        val actual = AlmanacMap(listOf(input)).get(98.toBigInteger())

        assertEquals(expected, actual)
    }

    @Test
    fun `given range(50, 98, 2) when get(99) then 51 returned`() {
        val input = AlmanacMap.Range(50.toBigInteger(), 98.toBigInteger(), 2.toBigInteger())
        val expected = 51.toBigInteger()

        val actual = AlmanacMap(listOf(input)).get(99.toBigInteger())

        assertEquals(expected, actual)
    }

    @Test
    fun `given range(50, 98, 2) when get(100) then 100 returned`() {
        val input = AlmanacMap.Range(50.toBigInteger(), 98.toBigInteger(), 2.toBigInteger())
        val expected = 100.toBigInteger()

        val actual = AlmanacMap(listOf(input)).get(100.toBigInteger())

        assertEquals(expected, actual)
    }

    @Test
    fun `given range(50, 98, 2) when get(97) then 97 returned`() {
        val input = AlmanacMap.Range(50.toBigInteger(), 98.toBigInteger(), 2.toBigInteger())
        val expected = 97.toBigInteger()

        val actual = AlmanacMap(listOf(input)).get(97.toBigInteger())

        assertEquals(expected, actual)
    }

    @Test
    fun `given ranges (50, 0, 10),(60, 10, 10) when get(12) then 62 returned`() {
        val input = listOf(
            AlmanacMap.Range(50.toBigInteger(), 0.toBigInteger(), 10.toBigInteger()),
            AlmanacMap.Range(60.toBigInteger(), 10.toBigInteger(), 10.toBigInteger()),
        )
        val expected = 62.toBigInteger()

        val actual = AlmanacMap(input).get(12.toBigInteger())

        assertEquals(expected, actual)
    }
}