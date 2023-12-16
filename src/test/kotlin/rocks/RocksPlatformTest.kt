package rocks

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RocksPlatformTest {

    @Test
    fun `given empty platform 3x2 when toString() then empty 3x2 returned`() {
        val sut = RocksPlatform(width = 3, height = 2)
        val expected = """
            ...
            ...
        """.trimIndent()

        val actual = sut.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given empty platform 3x2 when putRock() then toString() returns correct 3x2`() {
        val sut = RocksPlatform(width = 3, height = 2)
        val expected = """
            .O.
            ..#
        """.trimIndent()

        val actual = sut.apply {
            putRock(movable = true, x = 1, y = 0)
            putRock(movable = false, x = 2, y = 1)
        }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform 3x4 with rocks when tiltNorth() then toString() returns correct 3x4`() {
        //.##O
        //..O.
        //.O.O
        //O..O
        val sut = RocksPlatform(width = 4, height = 4).apply {
            putRock(movable = false, x = 1, y = 0)
            putRock(movable = false, x = 2, y = 0)
            putRock(movable = true, x = 0, y = 3)
            putRock(movable = true, x = 1, y = 2)
            putRock(movable = true, x = 2, y = 1)
            putRock(movable = true, x = 3, y = 0)
            putRock(movable = true, x = 3, y = 2)
            putRock(movable = true, x = 3, y = 3)
        }
        val expected = """
            O##O
            .OOO
            ...O
            ....
        """.trimIndent()

        val actual = sut.apply { tiltNorth() }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform 3x4 with rocks when tiltWest() then toString() returns correct 3x4`() {
        //.##O
        //..O.
        //.O.O
        //O..O
        val sut = RocksPlatform(width = 4, height = 4).apply {
            putRock(movable = false, x = 1, y = 0)
            putRock(movable = false, x = 2, y = 0)
            putRock(movable = true, x = 0, y = 3)
            putRock(movable = true, x = 1, y = 2)
            putRock(movable = true, x = 2, y = 1)
            putRock(movable = true, x = 3, y = 0)
            putRock(movable = true, x = 3, y = 2)
            putRock(movable = true, x = 3, y = 3)
        }
        val expected = """
            .##O
            O...
            OO..
            OO..
        """.trimIndent()

        val actual = sut.apply { tiltWest() }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform 4x1 with rocks when calculateLoadNorth() then 4 returned`() {
        //OOOO
        val sut = RocksPlatform(width = 4, height = 1).apply {
            putRock(movable = true, x = 0, y = 0)
            putRock(movable = true, x = 1, y = 0)
            putRock(movable = true, x = 2, y = 0)
            putRock(movable = true, x = 3, y = 0)
        }
        val expected = 4

        val actual = sut.calculateLoadNorth()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform 4x2 with rocks when calculateLoadNorth() then 2 returned`() {
        //O##OO
        //OOOO
        val sut = RocksPlatform(width = 4, height = 2).apply {
            putRock(movable = true, x = 0, y = 0)
            putRock(movable = false, x = 1, y = 0)
            putRock(movable = false, x = 2, y = 0)
            putRock(movable = true, x = 3, y = 0)
        }
        val expected = 4

        val actual = sut.calculateLoadNorth()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform with rocks when tiltNorth() then toString() returns tilted platform`() {
        val sut = RocksPlatform.parse("""
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent().split('\n'))
        val expected = """
            OOOO.#.O..
            OO..#....#
            OO..O##..O
            O..#.OO...
            ........#.
            ..#....#.#
            ..O..#.O.O
            ..O.......
            #....###..
            #....#....
        """.trimIndent()

        val actual = sut.apply { tiltNorth() }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform with rocks when tiltWest() then toString() returns tilted platform`() {
        val sut = RocksPlatform.parse("""
            OOOO.#.O..
            OO..#....#
            OO..O##..O
            O..#.OO...
            ........#.
            ..#....#.#
            ..O..#.O.O
            ..O.......
            #....###..
            #....#....
        """.trimIndent().split('\n'))
        val expected = """
            OOOO.#O...
            OO..#....#
            OOO..##O..
            O..#OO....
            ........#.
            ..#....#.#
            O....#OO..
            O.........
            #....###..
            #....#....
        """.trimIndent()

        val actual = sut.apply { tiltWest() }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform with rocks when tiltSouth() then toString() returns tilted platform`() {
        val sut = RocksPlatform.parse("""
            OOOO.#O...
            OO..#....#
            OOO..##O..
            O..#OO....
            ........#.
            ..#....#.#
            O....#OO..
            O.........
            #....###..
            #....#....
        """.trimIndent().split('\n'))
        val expected = """
            .....#....
            ....#.O..#
            O..O.##...
            O.O#......
            O.O....O#.
            O.#..O.#.#
            O....#....
            OO....OO..
            #O...###..
            #O..O#....
        """.trimIndent()

        val actual = sut.apply { tiltSouth() }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform with rocks when tiltEast() then toString() returns tilted platform`() {
        val sut = RocksPlatform.parse("""
            .....#....
            ....#.O..#
            O..O.##...
            O.O#......
            O.O....O#.
            O.#..O.#.#
            O....#....
            OO....OO..
            #O...###..
            #O..O#....
        """.trimIndent().split('\n'))
        val expected = """
            .....#....
            ....#...O#
            ...OO##...
            .OO#......
            .....OOO#.
            .O#...O#.#
            ....O#....
            ......OOOO
            #...O###..
            #..OO#....
        """.trimIndent()

        val actual = sut.apply { tiltEast() }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform with rocks when one tilt N-W-S-E then toString() returns tilted platform`() {
        val sut = RocksPlatform.parse("""
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent().split('\n'))
        val expected = """
            .....#....
            ....#...O#
            ...OO##...
            .OO#......
            .....OOO#.
            .O#...O#.#
            ....O#....
            ......OOOO
            #...O###..
            #..OO#....
        """.trimIndent()

        val actual = sut.apply {
            tiltNorth()
            tiltWest()
            tiltSouth()
            tiltEast()
        }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform with rocks when two tilts N-W-S-E then toString() returns tilted platform`() {
        val sut = RocksPlatform.parse("""
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent().split('\n'))
        val expected = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #..OO###..
            #.OOO#...O
        """.trimIndent()

        val actual = sut.apply {
            repeat(2) {
                tiltNorth()
                tiltWest()
                tiltSouth()
                tiltEast()
            }
        }.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `given platform with rocks when three tilts N-W-S-E then toString() returns tilted platform`() {
        val sut = RocksPlatform.parse("""
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent().split('\n'))
        val expected = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #...O###.O
            #.OOO#...O
        """.trimIndent()

        val actual = sut.apply {
            repeat(3) {
                tiltNorth()
                tiltWest()
                tiltSouth()
                tiltEast()
            }
        }.toString()

        assertEquals(expected, actual)
    }
}