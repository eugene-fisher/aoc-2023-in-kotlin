import poker.Bid
import poker.CamelCardsWithJokersParser
import poker.CamelCardsWithoutJokersParser
import poker.Game

fun main() {
    fun part1(input: List<String>): Int {
        val bidParser = Bid.Parser(CamelCardsWithoutJokersParser())
        val game = Game(bids = input.map { bidParser.parse(it) })
        return game.totalWinnings()
    }

    fun part2(input: List<String>): Int {
        val bidParser = Bid.Parser(CamelCardsWithJokersParser())
        val game = Game(bids = input.map { bidParser.parse(it) })
        return game.totalWinnings()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part2(testInput) == 5905)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
