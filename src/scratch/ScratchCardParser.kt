package scratch

class ScratchCardParser {

    private val cardRegex = Regex("Card\\s+([0-9]+):((\\s+[0-9]+)+ )\\|(.+)")

    fun parse(text: String): ScratchCard {
        val match = requireNotNull(cardRegex.find(text)) {
            "Cant parse ScratchCard $text"
        }
        return ScratchCard(
            id = match.groupValues[1].toInt(),
            winning = match.groupValues[2].split(' ').filter { it.isNotBlank() }.map { it.toInt() }.toSet(),
            have = match.groupValues[4].split(' ').filter { it.isNotBlank() }.map { it.toInt() }.toSet(),
        )
    }
}