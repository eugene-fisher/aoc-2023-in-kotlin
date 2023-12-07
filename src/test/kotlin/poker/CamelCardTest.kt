package poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CamelCardTest {

    @Test
    fun `given Card(2) when compared with Card(2) then returned 0`() {
        val input = CamelCard('2', 2)
        val expected = 0

        val actual = input.compareTo(CamelCard('2', 2))

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card(3) when compared with Card(2) then returned 1`() {
        val input = CamelCard('3', 3)
        val expected = 1

        val actual = input.compareTo(CamelCard('2', 2))

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card(2) when compared with Card(3) then returned -1`() {
        val input = CamelCard('2', 2)
        val expected = -1

        val actual = input.compareTo(CamelCard('3', 3))

        assertEquals(expected, actual)
    }
}