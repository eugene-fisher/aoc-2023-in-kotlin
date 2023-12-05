import almanac.AlmanacParser
import almanac.NumberOfSeedsParser
import almanac.RangeOfSeedsParser

fun main() {
    fun part1(input: List<String>): Int {
        val parser = AlmanacParser(seedsParser = NumberOfSeedsParser())
        input.forEach { parser.parseLine(it) }
        val almanac = parser.buildParsed()
        return almanac.seeds.minOf { almanac.get(it) }.toInt()
    }

    //Not optimized, takes tens of minutes
    fun part2(input: List<String>): Int {
        val parser = AlmanacParser(seedsParser = RangeOfSeedsParser())
        input.forEach { parser.parseLine(it) }
        val almanac = parser.buildParsed()
        return almanac.seeds.minOf { almanac.get(it) }.toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part2(testInput) == 46)

    val input = readInput("Day05")
    part1(input).also { check(it == 457535844) }.println()
    part2(input).println()
}
