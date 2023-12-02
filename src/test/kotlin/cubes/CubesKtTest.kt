package cubes

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CubesKtTest {

    @Test
    fun `given 4 red, 2 green, and 6 blue cubes when power called 48 returned`() {
        val input = Cubes(blue = 6, red = 4, green = 2)
        val expected = 48

        val actual = input.power

        assertEquals(expected, actual)
    }

    @Test
    fun `given 1 red, 3 green, and 4 blue cubes when power called 12 returned`() {
        val input = Cubes(blue = 4, red = 1, green = 3)
        val expected = 12

        val actual = input.power

        assertEquals(expected, actual)
    }

    @Test
    fun `given 20 red, 13 green, and 6 blue cubes when power called 1560 returned`() {
        val input = Cubes(red = 20, green = 13, blue = 6)
        val expected = 1560

        val actual = input.power

        assertEquals(expected, actual)
    }

    @Test
    fun `given 14 red, 3 green, and 15 blue cubes when power called 630 returned`() {
        val input = Cubes(red = 14, green = 3, blue = 15)
        val expected = 630

        val actual = input.power

        assertEquals(expected, actual)
    }

    @Test
    fun `given 6 red, 3 green, and 2 blue cubes when power called 36 returned`() {
        val input = Cubes(red = 6, green = 3, blue = 2)
        val expected = 36

        val actual = input.power

        assertEquals(expected, actual)
    }
}