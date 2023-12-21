import pipelines.Pipeline
import pipelines.PipelineFacility
import pipelines.PipelinePart

fun main() {
    fun part1(input: List<String>): Int {
        val facility = PipelineFacility(pipelines = input.takeWhile { it.isNotBlank() }.map { Pipeline.parse(it) })
        input.takeLastWhile { it.isNotBlank() }.forEach {
            facility.embark(part = PipelinePart.parse(it))
        }
        return facility.accepted.sumOf { it.x + it.m + it.a + it.s }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day19_test")
    check(part1(testInput) == 19114)

    val input = readInput("Day19")
    part1(input).println()
    part2(input).println()
}
