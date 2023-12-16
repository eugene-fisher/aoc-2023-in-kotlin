package rocks

class RocksPlatform(
    val width: Int,
    val height: Int,
) {
    private val grid = Array(width * height) { Rock.EMPTY }

    private fun index(x: Int, y: Int): Int = x + y * width

    override fun toString() = buildString {
        (0..<height).forEach { lineNumber ->
            val line = grid.drop(width * lineNumber).take(width).joinToString("") {
                when (it) {
                    Rock.EMPTY -> "."
                    Rock.MOVABLE -> "O"
                    Rock.STATIC -> "#"
                }
            }
            append(line)
            if (lineNumber != height - 1) {
                append('\n')
            }
        }
    }

    fun putRock(movable: Boolean, x: Int, y: Int) {
        grid[x + width * y] = if (movable) Rock.MOVABLE else Rock.STATIC
    }

    fun tiltNorth() {
        (1..<height).forEach { y ->
            (0..<width).forEach { x ->
                if (grid[index(x, y)] == Rock.MOVABLE) {
                    slide(TiltDirection.NORTH, x, y)
                }
            }
        }
    }

    fun tiltWest() {
        (1..<width).forEach { x ->
            (0..<height).forEach { y ->
                if (grid[index(x, y)] == Rock.MOVABLE) {
                    slide(TiltDirection.WEST, x, y)
                }
            }
        }
    }

    fun tiltSouth() {
        ((height - 2) downTo 0).forEach { y ->
            (0..<width).forEach { x ->
                if (grid[index(x, y)] == Rock.MOVABLE) {
                    slide(TiltDirection.SOUTH, x, y)
                }
            }
        }
    }

    fun tiltEast() {
        ((width - 2) downTo 0).forEach { x ->
            (0..<height).forEach { y ->
                if (grid[index(x, y)] == Rock.MOVABLE) {
                    slide(TiltDirection.EAST, x, y)
                }
            }
        }
    }

    private fun slide(direction: TiltDirection, x: Int, y: Int) {
        var fromX = x
        var toX = nextX(x, direction)
        var fromY = y
        var toY = nextY(y, direction)
        while (toX in (0..<width) && toY in (0..<height) && grid[index(toX, toY)] == Rock.EMPTY) {
            grid[index(toX, toY)] = grid[index(fromX, fromY)]
            grid[index(fromX, fromY)] = Rock.EMPTY
            fromX = toX
            fromY = toY
            toX = nextX(toX, direction)
            toY = nextY(toY, direction)
        }
    }

    private fun nextX(x: Int, direction: TiltDirection) = when (direction) {
        TiltDirection.WEST -> x - 1
        TiltDirection.EAST -> x + 1
        TiltDirection.NORTH, TiltDirection.SOUTH -> x
    }

    private fun nextY(y: Int, direction: TiltDirection) = when (direction) {
        TiltDirection.NORTH -> y - 1
        TiltDirection.SOUTH -> y + 1
        TiltDirection.WEST, TiltDirection.EAST -> y
    }

    fun calculateLoadNorth(): Int {
        return grid.foldIndexed(0) { index, acc, rock ->
            val y = index / width
            acc + if (rock == Rock.MOVABLE) height - y else 0
        }
    }

    private enum class Rock {
        EMPTY,
        MOVABLE,
        STATIC,
    }

    private enum class TiltDirection {
        NORTH,
        WEST,
        SOUTH,
        EAST,
    }

    companion object {
        fun parse(input: List<String>) = RocksPlatform(width = input.first().length, height = input.size).apply {
            input.forEachIndexed { y, line ->
                line.forEachIndexed { x, symbol ->
                    when (symbol) {
                        'O' -> putRock(movable = true, x, y)
                        '#' -> putRock(movable = false, x, y)
                    }
                }
            }
        }
    }
}