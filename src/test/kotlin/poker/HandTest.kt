package poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HandTest {

    @Test
    fun `given Hand(AAAAA) when type FIVE_OF_A_KIND returned`() {
        val input = Hand(
            listOf(
                CamelCard('A', 1),
                CamelCard('A', 1),
                CamelCard('A', 1),
                CamelCard('A', 1),
                CamelCard('A', 1),
            )
        )
        val expected = Hand.Type.FIVE_OF_A_KIND

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(AA8AA) when type FOUR_OF_A_KIND returned`() {
        val input = Hand(
            listOf(
                CamelCard('A', 1),
                CamelCard('A', 1),
                CamelCard('8', 0),
                CamelCard('A', 1),
                CamelCard('A', 1),
            )
        )
        val expected = Hand.Type.FOUR_OF_A_KIND

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(23332) when type FULL_HOUSE returned`() {
        val input = Hand(
            listOf(
                CamelCard('2', 2),
                CamelCard('3', 3),
                CamelCard('3', 3),
                CamelCard('3', 3),
                CamelCard('2', 2),
            )
        )
        val expected = Hand.Type.FULL_HOUSE

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(TTT98) when type THREE_OF_A_KIND returned`() {
        val input = Hand(
            listOf(
                CamelCard('T', 10),
                CamelCard('T', 10),
                CamelCard('T', 10),
                CamelCard('9', 9),
                CamelCard('8', 8),
            )
        )
        val expected = Hand.Type.THREE_OF_A_KIND

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(23432) when type TWO_PAIR returned`() {
        val input = Hand(
            listOf(
                CamelCard('2', 2),
                CamelCard('3', 3),
                CamelCard('4', 4),
                CamelCard('3', 3),
                CamelCard('2', 2),
            )
        )
        val expected = Hand.Type.TWO_PAIR

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(A23A4) when type ONE_PAIR returned`() {
        val input = Hand(
            listOf(
                CamelCard('A', 10),
                CamelCard('2', 2),
                CamelCard('3', 3),
                CamelCard('A', 10),
                CamelCard('4', 4),
            )
        )
        val expected = Hand.Type.ONE_PAIR

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(23456) when type HIGH_CARD returned`() {
        val input = Hand(
            listOf(
                CamelCard('2', 2),
                CamelCard('3', 3),
                CamelCard('4', 4),
                CamelCard('5', 5),
                CamelCard('6', 6),
            )
        )
        val expected = Hand.Type.HIGH_CARD

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(33332) and Hand(2AAAA) when compared first is stronger`() {
        val input1 = Hand(
            listOf(
                CamelCard('3', 3),
                CamelCard('3', 3),
                CamelCard('3', 3),
                CamelCard('3', 3),
                CamelCard('2', 2),
            )
        )
        val input2 = Hand(
            listOf(
                CamelCard('2', 2),
                CamelCard('A', 10),
                CamelCard('A', 10),
                CamelCard('A', 10),
                CamelCard('A', 10),
            )
        )
        val expected = true

        val actual = input1 > input2

        assertEquals(expected, actual)
    }

    @Test
    fun `KK677 is stronger than KTJJT`() {
        val input1 = Hand(
            listOf(
                CamelCard('K', 13),
                CamelCard('K', 13),
                CamelCard('6', 6),
                CamelCard('7', 7),
                CamelCard('7', 7),
            )
        )
        val input2 = Hand(
            listOf(
                CamelCard('K', 13),
                CamelCard('T', 10),
                CamelCard('J', 11),
                CamelCard('J', 11),
                CamelCard('T', 10),
            )
        )
        val expected = true

        val actual = input1 > input2

        assertEquals(expected, actual)
    }

    @Test
    fun `T55J5 is stronger than KTJJT`() {
        val input1 = Hand(
            listOf(
                CamelCard('T', 10),
                CamelCard('5', 5),
                CamelCard('5', 5),
                CamelCard('J', 11),
                CamelCard('5', 5),
            )
        )
        val input2 = Hand(
            listOf(
                CamelCard('K', 13),
                CamelCard('T', 10),
                CamelCard('J', 11),
                CamelCard('J', 11),
                CamelCard('T', 10),
            )
        )
        val expected = true

        val actual = input1 > input2

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(T55j5) when type FOUR_OF_A_KIND returned`() {
        val input = Hand(
            listOf(
                CamelCard('T', 10),
                CamelCard('5', 5),
                CamelCard('5', 5),
                CamelCard('J', 0, isWild = true),
                CamelCard('5', 5),
            )
        )
        val expected = Hand.Type.FOUR_OF_A_KIND

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(KTjjT) when type FOUR_OF_A_KIND returned`() {
        val input = Hand(
            listOf(
                CamelCard('K', 13),
                CamelCard('T', 10),
                CamelCard('J', 0, isWild = true),
                CamelCard('J', 0, isWild = true),
                CamelCard('T', 10),
            )
        )
        val expected = Hand.Type.FOUR_OF_A_KIND

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(QQQJA) when type FOUR_OF_A_KIND returned`() {
        val input = Hand(
            listOf(
                CamelCard('Q', 12),
                CamelCard('Q', 12),
                CamelCard('Q', 12),
                CamelCard('J', 0, isWild = true),
                CamelCard('A', 14),
            )
        )
        val expected = Hand.Type.FOUR_OF_A_KIND

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `Hand(KTjjT) more Hand(QQQjA) more Hand(T55j5)`() {
        val input1 = Hand(
            listOf(
                CamelCard('K', 13),
                CamelCard('T', 10),
                CamelCard('J', 0, isWild = true),
                CamelCard('J', 0, isWild = true),
                CamelCard('T', 10),
            )
        )
        val input2 = Hand(
            listOf(
                CamelCard('Q', 12),
                CamelCard('Q', 12),
                CamelCard('Q', 12),
                CamelCard('J', 0, isWild = true),
                CamelCard('A', 14),
            )
        )
        val input3 = Hand(
            listOf(
                CamelCard('T', 10),
                CamelCard('5', 5),
                CamelCard('5', 5),
                CamelCard('J', 0, isWild = true),
                CamelCard('5', 5),
            )
        )
        val expected = true

        val actual = (input1 > input2) && (input2 > input3)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(j2T2T) when type FULL_HOUSE returned`() {
        val input = Hand(
            listOf(
                CamelCard('j', 0, isWild = true),
                CamelCard('2', 2),
                CamelCard('T', 10),
                CamelCard('2', 2),
                CamelCard('T', 10),
            )
        )
        val expected = Hand.Type.FULL_HOUSE

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(7jj3Q) when type THREE_OF_A_KIND returned`() {
        val input = Hand(
            listOf(
                CamelCard('7', 7),
                CamelCard('j', 0, isWild = true),
                CamelCard('j', 0, isWild = true),
                CamelCard('3', 3),
                CamelCard('Q', 12),
            )
        )
        val expected = Hand.Type.THREE_OF_A_KIND

        val actual = input.type

        assertEquals(expected, actual)
    }

    @Test
    fun `given Hand(A298j) when type ONE_PAIR returned`() {
        val input = Hand(
            listOf(
                CamelCard('A', 14),
                CamelCard('2', 2),
                CamelCard('9', 9),
                CamelCard('8', 8),
                CamelCard('j', 0, isWild = true),
            )
        )
        val expected = Hand.Type.ONE_PAIR

        val actual = input.type

        assertEquals(expected, actual)
    }
}