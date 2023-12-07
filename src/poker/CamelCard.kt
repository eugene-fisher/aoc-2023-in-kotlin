package poker

data class CamelCard(
    private val card: Char,
    private val power: Int,
    val isWild: Boolean = false,
) : Comparable<CamelCard> {

    override fun toString(): String = if (isWild) "*" else card.toString()
    override fun compareTo(other: CamelCard) = power.compareTo(other.power)
}