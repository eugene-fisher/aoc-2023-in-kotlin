import bricks.Brick
import bricks.BricksStack

fun main() {
    fun part1(input: List<String>): Int {
        val stack = BricksStack()
        input.map { Brick.parse(it) }.sortedBy { it.bottom.first().z }
            .forEach { stack.drop(it) }
        stack.hypotheticalDisintegration()
        return stack.safeToDisintegrate
    }

    fun part2(input: List<String>): Int {
        val stack = BricksStack()
        input.map { Brick.parse(it) }.sortedBy { it.bottom.first().z }
            .forEach { stack.drop(it) }
        stack.hypotheticalDisintegration()
        return stack.totalDisintegrationImpact
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day22_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 7)

    val input = readInput("Day22")
    part1(input).println()
    part2(input).println()
}
