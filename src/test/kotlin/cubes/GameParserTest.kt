package cubes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameParserTest {

    private val sut = GameParser()

    @Test
    fun `given Game 1 when parsGame() correct Game returned`() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        val expected = Game(
            id = 1,
            revealed = listOf(
                Cubes(blue = 3, red = 4),
                Cubes(red = 1, green = 2, blue = 6),
                Cubes(green = 2),
            ),
        )

        val actual = sut.parseGame(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 2 when parsGame() correct Game returned`() {
        val input = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"
        val expected = Game(
            id = 2,
            revealed = listOf(
                Cubes(blue = 1, green = 2),
                Cubes(red = 1, green = 3, blue = 4),
                Cubes(green = 1, blue = 1),
            ),
        )

        val actual = sut.parseGame(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 3 when parsGame() correct Game returned`() {
        val input = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"
        val expected = Game(
            id = 3,
            revealed = listOf(
                Cubes(blue = 6, green = 8, red = 20),
                Cubes(red = 4, green = 13, blue = 5),
                Cubes(green = 5, red = 1),
            ),
        )

        val actual = sut.parseGame(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 4 when parsGame() correct Game returned`() {
        val input = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
        val expected = Game(
            id = 4,
            revealed = listOf(
                Cubes(blue = 6, green = 1, red = 3),
                Cubes(red = 6, green = 3),
                Cubes(green = 3, red = 14, blue = 15),
            ),
        )

        val actual = sut.parseGame(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Game 5 when parsGame() correct Game returned`() {
        val input = "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        val expected = Game(
            id = 5,
            revealed = listOf(
                Cubes(red = 6, blue = 1, green = 3),
                Cubes(blue = 2, red = 1, green = 2),
            ),
        )

        val actual = sut.parseGame(input)

        assertEquals(expected, actual)
    }
}