package maze

class Maze(
    private val width: Int,
    private val height: Int,
) {
    private val grid = mutableListOf<MazeTile>()

    private var terminatorIndex: Int? = null

    private val mainLoop = HashSet<MazeTile.Position>()
    val mainLoopLength: Int get() = mainLoop.size

    fun addTile(tile: MazeTile) {
        grid.add(tile)
        if (tile is MazeTile.Terminator) {
            terminatorIndex = grid.size - 1
        }
    }

    fun connectTiles() {
        var previous: MazeTile.Position = requireNotNull(terminatorIndex).let { index ->
            MazeTile.Position(x = index % width, y = index / width)
        }
        var tile: MazeTile = closestPipe(previous)
        var current: MazeTile.Position = grid.indexOf(tile).let { index ->
            MazeTile.Position(x = index % width, y = index / width)
        }
        while (tile is MazeTile.Pipe) {
            mainLoop.add(current)
            val tmp = current
            val side1ToPrevious = current.translated(by = tile.side1.relativePosition) == previous
            current = current.translated(by = if (side1ToPrevious) tile.side2.relativePosition else tile.side1.relativePosition)
            previous = tmp
            tile = grid[index(current)]
        }
        require(tile is MazeTile.Terminator)
        mainLoop.add(current)
    }

    private fun closestPipe(position: MazeTile.Position): MazeTile.Pipe {
        return listOfNotNull(
            findTile(position, MazeTile.Position(0, 1)),
            findTile(position, MazeTile.Position(1, 0)),
            findTile(position, MazeTile.Position(-1, 0)),
            findTile(position, MazeTile.Position(0, -1)),
        ).first { it is MazeTile.Pipe } as MazeTile.Pipe
    }

    private fun findTile(position: MazeTile.Position, relative: MazeTile.Position): MazeTile? {
        return relative.translated(by = position)
            .takeIf { it.isWithinBounds() }
            ?.let { grid[index(it)] }
    }

    val tilesInside: Int get() {
        var counter = 0
        for (index in grid.indices) {
            val position = MazeTile.Position(x = index % width, y = index / width)
            if (position in mainLoop) {
                continue
            }
            if (position.isInsideOfMainLoop()) {
                counter++
            }
        }
        return counter
    }

    private fun castRay(from: MazeTile.Position): Int {
        var ray = from
        var intersections = 0
        while (ray.isWithinBounds()) {
            if (ray in mainLoop) {
                val tile = grid[index(ray)]
                if (tile.shape != 'L' && tile.shape != '7') {
                    intersections++
                }
            }
            ray = ray.translated(-1, -1)
        }
        return intersections
    }

    private fun index(position: MazeTile.Position) = position.x + position.y * width

    private fun MazeTile.Position.isWithinBounds() = x in 0..<width && y in 0..<height
    private fun MazeTile.Position.isInsideOfMainLoop(): Boolean {
        val intersections = castRay(from = this)
        return intersections % 2 == 1
    }
}