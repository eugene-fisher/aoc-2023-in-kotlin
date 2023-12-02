package cubes

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class GameKtTest {

    @Test
    fun `given Game 1 when minCubesPossible called 4 red, 2 green, and 6 blue cubes returned`() {
        val input = Game(
            id = 1,
            revealed = listOf(
                Cubes(blue = 3, red = 4),
                Cubes(red = 1, green = 2, blue = 6),
                Cubes(green = 2),
            ),
        )
        val expected = Cubes(red = 4, green = 2, blue = 6)

        val actual = input.minCubesPossible

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 2 when minCubesPossible called 1 red, 3 green, and 4 blue cubes returned`() {
        val input = Game(
            id = 2,
            revealed = listOf(
                Cubes(blue = 1, green = 2),
                Cubes(red = 1, green = 3, blue = 4),
                Cubes(green = 1, blue = 1),
            ),
        )
        val expected = Cubes(red = 1, green = 3, blue = 4)

        val actual = input.minCubesPossible

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 3 when minCubesPossible called 20 red, 13 green, and 6 blue cubes returned`() {
        val input = Game(
            id = 3,
            revealed = listOf(
                Cubes(blue = 6, green = 8, red = 20),
                Cubes(red = 4, green = 13, blue = 5),
                Cubes(green = 5, red = 1),
            ),
        )
        val expected = Cubes(red = 20, green = 13, blue = 6)

        val actual = input.minCubesPossible

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 4 when minCubesPossible called 14 red, 3 green, and 15 blue cubes returned`() {
        val input = Game(
            id = 4,
            revealed = listOf(
                Cubes(blue = 6, green = 1, red = 3),
                Cubes(red = 6, green = 3),
                Cubes(green = 3, red = 14, blue = 15),
            ),
        )
        val expected = Cubes(red = 14, green = 3, blue = 15)

        val actual = input.minCubesPossible

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 5 when minCubesPossible called 6 red, 3 green, and 2 blue cubes returned`() {
        val input = Game(
            id = 5,
            revealed = listOf(
                Cubes(red = 6, blue = 1, green = 3),
                Cubes(blue = 2, red = 1, green = 2),
            ),
        )
        val expected = Cubes(red = 6, green = 3, blue = 2)

        val actual = input.minCubesPossible

        assertEquals(expected, actual)
    }
}