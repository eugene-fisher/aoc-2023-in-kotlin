import race.RacesParser
import race.waysToWin

fun main() {
    fun part1(input: List<String>): Int {
        val races = RacesParser().parseRaces(input[0], input[1])
        return races.fold(1) { acc, race -> acc * race.waysToWin() }
    }

    fun part2(input: List<String>): Int {
        val race = RacesParser().parseRace(input[0], input[1])
        return race.waysToWin()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part2(testInput) == 71503)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
