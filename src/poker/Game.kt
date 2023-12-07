package poker

class Game(private val bids: List<Bid>) {

    fun totalWinnings(): Int {
        val ranks = bids.sortedBy { it.hand }
        return ranks.foldRightIndexed(0) { index, bid, acc ->
            val rank = index + 1
            acc + bid.amount * rank
        }
    }
}