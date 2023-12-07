package poker

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BidTest {

    @Test
    fun `given 32T3K 765 when parse() then Bid returned`() {
        val input = "32T3K 765"
        val expected = Bid(
            hand = Hand(
                listOf(
                    CamelCard('3', 2),
                    CamelCard('2', 1),
                    CamelCard('T', 10),
                    CamelCard('3', 2),
                    CamelCard('K', 13),
                )
            ),
            amount = 765,
        )

        val actual = Bid.Parser(CamelCardsWithoutJokersParser()).parse(input)

        assertEquals(expected, actual)
    }
}