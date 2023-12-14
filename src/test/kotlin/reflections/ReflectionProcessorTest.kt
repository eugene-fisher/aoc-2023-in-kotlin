package reflections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReflectionProcessorTest {

    private val sut = ReflectionProcessor()

    @Test
    fun findReflectionColumn1() {
        //#.##..##.
        //..#.##.#.
        //##......#
        val input = ReflectedImage(width = 9, height = 3).apply {
            initNext(true)
            initNext(false)
            initNext(true)
            initNext(true)
            initNext(false)
            initNext(false)
            initNext(true)
            initNext(true)
            initNext(false)

            initNext(false)
            initNext(false)
            initNext(true)
            initNext(false)
            initNext(true)
            initNext(true)
            initNext(false)
            initNext(true)
            initNext(false)

            initNext(true)
            initNext(true)
            initNext(false)
            initNext(false)
            initNext(false)
            initNext(false)
            initNext(false)
            initNext(false)
            initNext(true)
        }
        val expected = 5

        val actual = sut.findReflectionColumn(input)

        assertEquals(expected, actual)
    }

    @Test
    fun findReflectionColumn2() {
        //#.##..
        //..#.##
        //##....
        val input = ReflectedImage(width = 6, height = 3).apply {
            initNext(true)
            initNext(false)
            initNext(true)
            initNext(true)
            initNext(false)
            initNext(false)

            initNext(false)
            initNext(false)
            initNext(true)
            initNext(false)
            initNext(true)
            initNext(true)

            initNext(true)
            initNext(true)
            initNext(false)
            initNext(false)
            initNext(false)
            initNext(false)
        }
        val expected = 5

        val actual = sut.findReflectionColumn(input)

        assertEquals(expected, actual)
    }

    @Test
    fun findReflectionRow1() {
        //  #.#
        //  #..
        //  ..#
        //v ###
        //^ ###
        //  ..#
        //  #..
        val input = ReflectedImage(width = 3, height = 7).apply {
            initNext(true)
            initNext(false)
            initNext(true)

            initNext(true)
            initNext(false)
            initNext(false)

            initNext(false)
            initNext(false)
            initNext(true)

            initNext(true)
            initNext(true)
            initNext(true)

            initNext(true)
            initNext(true)
            initNext(true)

            initNext(false)
            initNext(false)
            initNext(true)

            initNext(true)
            initNext(false)
            initNext(false)
        }
        val expected = 4

        val actual = sut.findReflectionRow(input)

        assertEquals(expected, actual)
    }
}