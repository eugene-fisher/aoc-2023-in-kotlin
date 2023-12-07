package poker

data class Hand(val cards: List<CamelCard>) : Comparable<Hand> {

    val type: Type

    init {
        require(cards.size == 5)

        val occurrences = cards.groupingBy { it }.eachCount()
        val sortedOccurrences = occurrences.entries.filter { !it.key.isWild }.map { it.value }.sortedDescending()
        val wildCards = occurrences.firstNotNullOfOrNull { (key, value) -> value.takeIf { key.isWild } } ?: 0
        type = when ((sortedOccurrences.firstOrNull() ?: 0) + wildCards) {
            5 -> Type.FIVE_OF_A_KIND
            4 -> Type.FOUR_OF_A_KIND
            3 -> if (sortedOccurrences.size == 2) Type.FULL_HOUSE else Type.THREE_OF_A_KIND
            2 -> if (sortedOccurrences.size == 3) Type.TWO_PAIR else Type.ONE_PAIR
            else -> Type.HIGH_CARD
        }
    }

    override fun compareTo(other: Hand): Int {
        val typesComparison = type.compareTo(other.type)
        if (typesComparison != 0) {
            return typesComparison
        } else {
            cards.indices.forEach {
                val comparisonResult = cards[it].compareTo(other.cards[it])
                if (comparisonResult != 0) return comparisonResult
            }
            return 0
        }
    }

    enum class Type {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND,
    }
}