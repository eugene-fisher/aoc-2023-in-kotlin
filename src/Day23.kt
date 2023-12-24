import hikes.HikingMap

fun main() {
    fun part1(input: List<String>): Int {
        val tiles = input.flatMap { line ->
            line.map {
                when (it) {
                    '.' -> HikingMap.Tile.PATH
                    '#' -> HikingMap.Tile.FOREST
                    '>' -> HikingMap.Tile.SLOPE_RIGHT
                    '<' -> HikingMap.Tile.SLOPE_LEFT
                    'v' -> HikingMap.Tile.SLOPE_DOWN
                    '^' -> HikingMap.Tile.SLOPE_UP
                    else -> throw IllegalArgumentException("Unknown tile $it")
                }
            }
        }
        val map = HikingMap(width = input.first().length, height = input.size, tiles.toTypedArray())
        return map.routes(fromIndex = 1, toIndex = tiles.lastIndex - 1).max() - 1
    }

    fun part2(input: List<String>): Int {
        val tiles = input.flatMap { line ->
            line.map {
                when (it) {
                    '.', '>', '<', 'v', '^' -> HikingMap.Tile.PATH
                    '#' -> HikingMap.Tile.FOREST
                    else -> throw IllegalArgumentException("Unknown tile $it")
                }
            }
        }
        val map = HikingMap(width = input.first().length, height = input.size, tiles.toTypedArray())
        return map.routes(fromIndex = 1, toIndex = tiles.lastIndex - 1).max() - 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day23_test")
    check(part1(testInput) == 94)
    check(part2(testInput) == 154)

    val input = readInput("Day23")
    part1(input).println()
    part2(input).println()
}
