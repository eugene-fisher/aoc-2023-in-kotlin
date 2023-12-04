package scratch

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ScratchCardTest {

    @Test
    fun `given Card 41 48 83 86 17 ! 83 86  6 31 17  9 48 53 when checkValue then 8 returned`() {
        val input = ScratchCard(id = 0, winning = setOf(41, 48, 83, 86, 17), have = setOf(83, 86, 6, 31, 17, 9, 48, 53))
        val expected = 8

        val actual = input.checkValue()

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card 13 32 20 16 61 ! 61 30 68 82 17 32 24 19 when checkValue then 2 returned`() {
        val input = ScratchCard(id = 0, winning = setOf(13, 32, 20, 16, 61), have = setOf(61, 30, 68, 82, 17, 32, 24, 19))
        val expected = 2

        val actual = input.checkValue()

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card 1 21 53 59 44 ! 69 82 63 72 16 21 14  1 when checkValue then 2 returned`() {
        val input = ScratchCard(id = 0, winning = setOf(1, 21, 53, 59, 44), have = setOf(69, 82, 63, 72, 16, 21, 14, 1))
        val expected = 2

        val actual = input.checkValue()

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card 41 92 73 84 69 ! 59 84 76 51 58  5 54 83 when checkValue then 1 returned`() {
        val input = ScratchCard(id = 0, winning = setOf(41, 92, 73, 84, 69), have = setOf(59, 84, 76, 51, 58, 5, 54, 83))
        val expected = 1

        val actual = input.checkValue()

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card 87 83 26 28 32 ! 88 30 70 12 93 22 82 36 when checkValue then 0 returned`() {
        val input = ScratchCard(id = 0, winning = setOf(87, 83, 26, 28, 32), have = setOf(88, 30, 70, 12, 93, 22, 82, 36))
        val expected = 0

        val actual = input.checkValue()

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card 31 18 13 56 72 ! 74 77 10 23 35 67 36 11 when checkValue then 0 returned`() {
        val input = ScratchCard(id = 0, winning = setOf(31, 18, 13, 56, 72), have = setOf(74, 77, 10, 23, 35, 67, 36, 11))
        val expected = 0

        val actual = input.checkValue()

        assertEquals(expected, actual)
    }
}