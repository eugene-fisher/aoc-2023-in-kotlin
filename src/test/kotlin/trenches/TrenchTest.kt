package trenches

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TrenchTest {

    @Test
    fun `given 2x2 rect when area then 4 returned`() {
        //##
        //##
        val sut = Trench()
        val expected = 4.0

        sut.dig(Trench.Direction.RIGHT, 1)
        sut.dig(Trench.Direction.DOWN, 1)
        sut.dig(Trench.Direction.LEFT, 1)
        sut.dig(Trench.Direction.UP, 1)

        val actual = sut.area

        assertEquals(expected, actual)
    }

    @Test
    fun `given 6x2 rect when area then 12 returned`() {
        //######
        //######
        val sut = Trench()
        val expected = 12.0

        sut.dig(Trench.Direction.RIGHT, 5)
        sut.dig(Trench.Direction.DOWN, 1)
        sut.dig(Trench.Direction.LEFT, 5)
        sut.dig(Trench.Direction.UP, 1)

        val actual = sut.area

        assertEquals(expected, actual)
    }

    @Test
    fun `given 6x6x4x3x2x3 when area then 36 returned`() {
        //######
        //######
        //######
        //..####
        //..####
        //..####
        val sut = Trench()
        val expected = 30.0

        sut.dig(Trench.Direction.RIGHT, 5)
        sut.dig(Trench.Direction.DOWN, 5)
        sut.dig(Trench.Direction.LEFT, 3)
        sut.dig(Trench.Direction.UP, 3)
        sut.dig(Trench.Direction.LEFT, 2)
        sut.dig(Trench.Direction.UP, 2)

        val actual = sut.area

        assertEquals(expected, actual)
    }
}