package scratch

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ScratchCardParserTest {

    private val sut = ScratchCardParser()

    @Test
    fun `given Card1 41 48 83 86 17 ! 83 86  6 31 17  9 48 53 when parse then ScratchCard returned`() {
        val input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
        val expected = ScratchCard(id = 1, winning = setOf(41, 48, 83, 86, 17), have = setOf(83, 86, 6, 31, 17, 9, 48, 53))

        val actual = sut.parse(input)

        assertEquals(expected, actual)
    }


    @Test
    fun `given Card2 13 32 20 16 61 ! 61 30 68 82 17 32 24 19 when parse then ScratchCard returned`() {
        val input = "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"
        val expected = ScratchCard(id = 2, winning = setOf(13, 32, 20, 16, 61), have = setOf(61, 30, 68, 82, 17, 32, 24, 19))

        val actual = sut.parse(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card3 1 21 53 59 44 ! 69 82 63 72 16 21 14  1 when parse then ScratchCard returned`() {
        val input = "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"
        val expected = ScratchCard(id = 3, winning = setOf(1, 21, 53, 59, 44), have = setOf(69, 82, 63, 72, 16, 21, 14, 1))

        val actual = sut.parse(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card4 41 92 73 84 69 ! 59 84 76 51 58  5 54 83 when checkValue then 1 returned`() {
        val input = "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"
        val expected = ScratchCard(id = 4, winning = setOf(41, 92, 73, 84, 69), have = setOf(59, 84, 76, 51, 58, 5, 54, 83))

        val actual = sut.parse(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card5 87 83 26 28 32 ! 88 30 70 12 93 22 82 36 when checkValue then 0 returned`() {
        val input = "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"
        val expected = ScratchCard(id = 5, winning = setOf(87, 83, 26, 28, 32), have = setOf(88, 30, 70, 12, 93, 22, 82, 36))

        val actual = sut.parse(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card6 31 18 13 56 72 ! 74 77 10 23 35 67 36 11 when checkValue then 0 returned`() {
        val input = "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
        val expected = ScratchCard(id = 6, winning = setOf(31, 18, 13, 56, 72), have = setOf(74, 77, 10, 23, 35, 67, 36, 11))

        val actual = sut.parse(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Card1 82 15 37 75 85 51 99 18 17  2 !  8 17 54 14 18 99  4 85 51 49 91 15 82 24 75 25 69 61 52 58 37 87  2 22 28 when checkValue then 0 returned`() {
        val input = "Card   1: 82 15 37 75 85 51 99 18 17  2 |  8 17 54 14 18 99  4 85 51 49 91 15 82 24 75 25 69 61 52 58 37 87  2 22 28"
        val expected = ScratchCard(id = 1, winning = setOf(82,15,37,75,85,51,99,18,17, 2), have = setOf(8,17,54,14,18,99, 4,85,51,49,91,15,82,24,75,25,69,61,52,58,37,87,2,22,28))

        val actual = sut.parse(input)

        assertEquals(expected, actual)
    }
}