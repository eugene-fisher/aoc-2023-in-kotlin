package space

import java.awt.Point
import kotlin.math.abs

class Space(map: List<String>) {
    private var width: Int = map.firstOrNull()?.length ?: 0
    private var height: Int = map.size

    private var galaxiesMap: List<Boolean>
    private val distancesMap = List(width * height) { Point(1, 1) }

    init {
        galaxiesMap = map.flatMap { line -> line.map { it == '#' } }
    }

    fun expand(power: Int = 2) {
        val emptyColumns = (0..<width).map { x ->
            (0..<height).none { y -> galaxiesMap[index(x, y)] }
        }
        val emptyRows = (0..<height).map { y ->
            (0..<width).none { x -> galaxiesMap[index(x, y)] }
        }
        emptyRows.forEachIndexed { y, shouldExpandRow ->
            emptyColumns.forEachIndexed { x, shouldExpandColumn ->
                if (shouldExpandColumn) {
                    distancesMap[index(x, y)].x = power
                }
                if (shouldExpandRow) {
                    distancesMap[index(x, y)].y = power
                }
            }
        }
    }

    fun calculateShortestPathBetweenGalaxies(): Long {
        val galaxyLocations = galaxiesMap.mapIndexedNotNull { index, isGalaxy -> point(index).takeIf { isGalaxy } }
        return galaxyLocations.uniquePairs().sumOf { (first, second) -> shortestPath(first, second).toLong() }
    }

    private fun index(x: Int, y: Int) = x + y * width

    private fun point(index: Int): Point {
        val row = index / width
        val column = index % width
        return Point().apply {
            x = distancesMap.drop(row * width).take(column).sumOf { it.x }
            y = distancesMap.foldRightIndexed(0) { i, point, acc ->
                acc + if (i / width < row && i % width == column) point.y else 0
            }
        }
    }

    companion object {

        private fun shortestPath(point1: Point, point2: Point) = abs(point1.x - point2.x) + abs(point1.y - point2.y)


        fun <T> List<T>.uniquePairs(): List<Pair<T, T>> {
            return flatMapIndexed { index: Int, one: T ->
                drop(index + 1).map { another: T -> Pair(one, another) }
            }
        }
    }
}