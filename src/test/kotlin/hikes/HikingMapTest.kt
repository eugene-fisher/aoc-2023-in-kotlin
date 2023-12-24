package hikes

import hikes.HikingMap.Tile.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HikingMapTest {

    @Test
    fun `given straight maze when routes() then single length of 5 returned`() {
        //#.###
        //#...#
        //###.#
        val sut = HikingMap(
            width = 5, height = 3, tiles = arrayOf(
                FOREST, PATH, FOREST, FOREST, FOREST,
                FOREST, PATH, PATH, PATH, FOREST,
                FOREST, FOREST, FOREST, PATH, FOREST,
            )
        )
        val expected = listOf(5)

        val actual = sut.routes(fromIndex = 1, toIndex = 13)

        assertEquals(expected, actual)
    }

    @Test
    fun `given branched maze when routes() then two lengths of 7 returned`() {
        //#.###
        //#...#
        //#.#.#
        //#...#
        //###.#
        val sut = HikingMap(
            width = 5, height = 5, tiles = arrayOf(
                FOREST, PATH, FOREST, FOREST, FOREST,
                FOREST, PATH, PATH, PATH, FOREST,
                FOREST, PATH, FOREST, PATH, FOREST,
                FOREST, PATH, PATH, PATH, FOREST,
                FOREST, FOREST, FOREST, PATH, FOREST,
            )
        )
        val expected = listOf(7, 7)

        val actual = sut.routes(fromIndex = 1, toIndex = 23)

        assertEquals(expected, actual)
    }

    @Test
    fun `given unequally branched maze when routes() then two lengths of 6 and 8 returned`() {
        //#.###
        //#...#
        //#.#..
        //#...#
        //#####
        val sut = HikingMap(
            width = 5, height = 5, tiles = arrayOf(
                FOREST, PATH, FOREST, FOREST, FOREST,
                FOREST, PATH, PATH, PATH, FOREST,
                FOREST, PATH, FOREST, PATH, PATH,
                FOREST, PATH, PATH, PATH, FOREST,
                FOREST, FOREST, FOREST, FOREST, FOREST,
            )
        )
        val expected = listOf(6, 8)

        val actual = sut.routes(fromIndex = 1, toIndex = 14)

        assertEquals(expected, actual)
    }

    @Test
    fun `given unequally branched maze with slopes when routes() then two lengths of 6 and 8 returned`() {
        //#.###
        //#.>.#
        //#v#..
        //#...#
        //#####
        val sut = HikingMap(
            width = 5, height = 5, tiles = arrayOf(
                FOREST, PATH, FOREST, FOREST, FOREST,
                FOREST, PATH, SLOPE_RIGHT, PATH, FOREST,
                FOREST, SLOPE_DOWN, FOREST, PATH, PATH,
                FOREST, PATH, PATH, PATH, FOREST,
                FOREST, FOREST, FOREST, FOREST, FOREST,
            )
        )
        val expected = listOf(6, 8)

        val actual = sut.routes(fromIndex = 1, toIndex = 14)

        assertEquals(expected, actual)
    }

    @Test
    fun `given unequally branched maze with blocking slopes when routes() then one lengths of 8 returned`() {
        //#.###
        //#.<.#
        //#v#..
        //#...#
        //#####
        val sut = HikingMap(
            width = 5, height = 5, tiles = arrayOf(
                FOREST, PATH, FOREST, FOREST, FOREST,
                FOREST, PATH, SLOPE_LEFT, PATH, FOREST,
                FOREST, SLOPE_DOWN, FOREST, PATH, PATH,
                FOREST, PATH, PATH, PATH, FOREST,
                FOREST, FOREST, FOREST, FOREST, FOREST,
            )
        )
        val expected = listOf(8)

        val actual = sut.routes(fromIndex = 1, toIndex = 14)

        assertEquals(expected, actual)
    }
}