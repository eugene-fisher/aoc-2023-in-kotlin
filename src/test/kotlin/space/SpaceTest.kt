package space

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import space.Space.Companion.uniquePairs

class SpaceTest {

    @Test
    fun `given 1,2,3,4,5 when uniquePairs() then 1-2, 1-3, 1-4, 1-5, 2-3, 2-4, 2-5, 3-4, 3-5, 4-5 returned`() {
        val input = listOf(1, 2, 3, 4, 5)
        val expected = listOf(1 to 2, 1 to 3, 1 to 4, 1 to 5, 2 to 3, 2 to 4, 2 to 5, 3 to 4, 3 to 5, 4 to 5)

        val actual = input.uniquePairs()

        assertEquals(expected, actual)
    }
}