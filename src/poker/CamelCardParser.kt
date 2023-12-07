package poker

import java.lang.IllegalArgumentException

interface CamelCardParser {

    fun parse(char: Char): CamelCard
}

class CamelCardsWithoutJokersParser : CamelCardParser {
    override fun parse(char: Char) = CamelCard(
        card = char,
        power = when (char) {
            '2' -> 1
            '3' -> 2
            '4' -> 3
            '5' -> 4
            '6' -> 5
            '7' -> 6
            '8' -> 7
            '9' -> 8
            'T' -> 10
            'J' -> 11
            'Q' -> 12
            'K' -> 13
            'A' -> 14
            else -> throw IllegalArgumentException("Not supported card $char")
        }
    )
}

class CamelCardsWithJokersParser : CamelCardParser {
    override fun parse(char: Char) = CamelCard(
        card = char,
        isWild = char == 'J',
        power = when (char) {
            'J' -> 0
            '2' -> 1
            '3' -> 2
            '4' -> 3
            '5' -> 4
            '6' -> 5
            '7' -> 6
            '8' -> 7
            '9' -> 8
            'T' -> 10
            'Q' -> 12
            'K' -> 13
            'A' -> 14
            else -> throw IllegalArgumentException("Not supported card $char")
        }
    )
}