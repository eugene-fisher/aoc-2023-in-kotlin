import maze.Maze
import maze.MazeTile

fun main() {
    fun part1(input: List<String>): Int {
        val maze = Maze(width = input.first().length, height = input.size)
        input.flatMap { line -> line.map { tile -> MazeTile.parse(tile) } }.forEach { maze.addTile(it) }
        maze.connectTiles()
        return maze.mainLoopLength / 2
    }

    fun part2(input: List<String>): Int {
        val maze = Maze(width = input.first().length, height = input.size)
        input.flatMap { line -> line.map { tile -> MazeTile.parse(tile) } }.forEach { maze.addTile(it) }
        maze.connectTiles()
        return maze.tilesInside
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part2(testInput) == 4)

    val input = readInput("Day10")
    part1(input).also { check(it == 7093) }.println()
    part2(input).also { check(it == 407) }.println()
}
