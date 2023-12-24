package hikes

class HikingMap(
    private val width: Int,
    private val height: Int,
    private val tiles: Array<Tile>,
) {

    enum class Tile {
        PATH,
        FOREST,
        SLOPE_DOWN,
        SLOPE_UP,
        SLOPE_LEFT,
        SLOPE_RIGHT,
    }

    fun routes(fromIndex: Int, toIndex: Int): List<Int> {
        val routes = mutableListOf<Route>()
        var toVisit = listOf(Route(fromIndex, visited = mutableListOf(fromIndex)))
        while (toVisit.isNotEmpty()) {
            val next = mutableListOf<Route>()
            toVisit.forEach { visiting ->
                neighbours(visiting.head).filter { it !in visiting.visited }.forEach { neighbourIndex ->
                    when (tiles[neighbourIndex]) {
                        Tile.PATH -> next.add(Route(neighbourIndex, visiting.visited + neighbourIndex))
                        Tile.FOREST -> Unit
                        Tile.SLOPE_DOWN -> if (!visiting.head.isBelow(neighbourIndex)) next.add(Route(neighbourIndex, visiting.visited + neighbourIndex))
                        Tile.SLOPE_UP -> if (!visiting.head.isAbove(neighbourIndex)) next.add(Route(neighbourIndex, visiting.visited + neighbourIndex))
                        Tile.SLOPE_LEFT -> if (!visiting.head.isLeftOf(neighbourIndex)) next.add(Route(neighbourIndex, visiting.visited + neighbourIndex))
                        Tile.SLOPE_RIGHT -> if (!visiting.head.isRightOf(neighbourIndex)) next.add(Route(neighbourIndex, visiting.visited + neighbourIndex))
                    }
                }
            }
            val (finished, running) = next.partition { it.head == toIndex }
            routes.addAll(finished)
            toVisit = running
        }
        return routes.map { it.visited.size }
    }

    private fun neighbours(index: Int): Set<Int> {
        return setOfNotNull(
            (index + 1).takeIf { withinBounds(it) },
            (index - 1).takeIf { withinBounds(it) },
            (index + width).takeIf { withinBounds(it) },
            (index - width).takeIf { withinBounds(it) },
        )
    }

    private fun withinBounds(index: Int): Boolean {
        val x = index % width
        val y = index / width
        return x in (0..<width) && y in (0..<height)
    }

    private fun Int.isBelow(neighbourIndex: Int): Boolean {
        return this - neighbourIndex == width
    }

    private fun Int.isAbove(neighbourIndex: Int): Boolean {
        return neighbourIndex - this == width
    }

    private fun Int.isLeftOf(neighbourIndex: Int): Boolean {
        return neighbourIndex - this == 1
    }

    private fun Int.isRightOf(neighbourIndex: Int): Boolean {
        return this - neighbourIndex == 1
    }

    private data class Route(val head: Int, val visited: List<Int>)

}
