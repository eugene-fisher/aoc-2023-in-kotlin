import cubes.*

fun main() {
    val parser = GameParser()
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val game = parser.parseGame(it)
            if (game.isPossible(total = Cubes(red = 12, green = 13, blue = 14))) {
                game.id
            } else 0
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val game = parser.parseGame(it)
            game.minCubesPossible.power
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
