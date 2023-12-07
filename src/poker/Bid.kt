package poker

data class Bid(
    val amount: Int,
    val hand: Hand,
) {

    class Parser(private val cardParser: CamelCardParser) {
        fun parse(line: String): Bid {
            val elements = line.split(' ')
            return Bid(hand = parseHand(elements[0]), amount = elements[1].toInt())
        }

        private fun parseHand(line: String) = Hand(cards = line.map { cardParser.parse(it) })
    }
}