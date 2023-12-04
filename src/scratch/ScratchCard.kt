package scratch

import kotlin.math.pow

data class ScratchCard(
    val id: Int,
    val winning: Set<Int>,
    val have: Set<Int>,
)

fun ScratchCard.checkValue(): Int {
    val wonCount = checkWonCount()
    return if (wonCount > 0) 2.0.pow(wonCount - 1).toInt() else 0
}

fun List<ScratchCard>.getCopies(haveCards: List<ScratchCard>): List<ScratchCard> {
    return haveCards.flatMap { card ->
        val wonCount = card.checkWonCount()
        if (wonCount > 0) {
            val wonCopies = subList(fromIndex = card.id, toIndex = card.id + wonCount)
            getCopies(wonCopies) + card
        } else {
            listOf(card)
        }
    }
}

private fun ScratchCard.checkWonCount(): Int = have.intersect(winning).size