package beams

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.math.abs

class BeamMap(
    val width: Int,
    val height: Int,
) {
    private val grid = Array(width * height) { Tile.EMPTY }
    private val energized = Array(width * height) { Touched() }
    val energizedTiles: Int get() = energized.count { it.anyDirection() }

    fun setTile(tile: Tile, x: Int, y: Int) {
        grid[index(x, y)] = tile
    }

    fun projectBeam(initial: Beam) {
        val beams = mutableListOf(initial)
        while (beams.isNotEmpty()) {
            val finishedBeams = mutableListOf<Beam>()
            val extraBeams = mutableListOf<Beam>()
            for (beam in beams) {
                val next = beam.next()
                if (next != null) {
                    beam.move(next)?.let { extraBeams.add(it) }
                    if (beam.touched(energized[index(beam.x, beam.y)])) {
                        finishedBeams.add(beam)
                    }
                    beam.touch(energized[index(beam.x, beam.y)])
                } else {
                    finishedBeams.add(beam)
                }
            }
            beams.removeAll(finishedBeams)
            beams.addAll(extraBeams)
        }
    }

    private fun Beam.move(next: Tile): Beam? {
        x += vx
        y += vy
        when (next) {
            Tile.MIRROR_BACKWARD -> {
                val tmp = vx
                vx = vy
                vy = tmp
            }

            Tile.MIRROR_FORWARD -> {
                val tmp = vx
                vx = -vy
                vy = -tmp
            }

            Tile.SPLITTER_HORIZONTAL -> if (abs(vy) == 1) {
                vx = 1
                vy = 0
                return Beam(x, y, -vx, vy)
            }

            Tile.SPLITTER_VERTICAL -> if (abs(vx) == 1) {
                vx = 0
                vy = 1
                return Beam(x, y, vx, -vy)
            }

            Tile.EMPTY -> Unit
        }
        return null
    }

    private fun Beam.next(): Tile? = if (nextX in 0..<width && nextY in 0..<height) {
        grid[index(nextX, nextY)]
    } else null

    private fun Beam.touched(touched: Touched): Boolean = when {
        vx == 1 && touched.right -> true
        vx == -1 && touched.left -> true
        vy == 1 && touched.down -> true
        vy == -1 && touched.up -> true
        else -> false
    }

    private fun Beam.touch(touched: Touched) = when {
        vx == 1 -> touched.right = true
        vx == -1 -> touched.left = true
        vy == 1 -> touched.down = true
        vy == -1 -> touched.up = true
        else -> throw IllegalStateException()
    }

    private fun index(x: Int, y: Int): Int = x + y * width

    fun clearEnergized() {
        energized.forEach {
            it.down = false
            it.up = false
            it.right = false
            it.left = false
        }
    }

    enum class Tile {
        EMPTY,
        SPLITTER_HORIZONTAL,
        SPLITTER_VERTICAL,
        MIRROR_BACKWARD,
        MIRROR_FORWARD,
    }

    class Beam(var x: Int, var y: Int, var vx: Int, var vy: Int) {
        val nextX: Int get() = x + vx
        val nextY: Int get() = y + vy
    }

    private class Touched(
        var up: Boolean = false,
        var down: Boolean = false,
        var right: Boolean = false,
        var left: Boolean = false,
    ) {
        fun anyDirection() = up || down || right || left
    }

    companion object {
        fun parse(input: List<String>): BeamMap {
            val map = BeamMap(width = input.first().length, height = input.size)
            input.forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    map.setTile(
                        tile = when (c) {
                            '.' -> Tile.EMPTY
                            '\\' -> Tile.MIRROR_BACKWARD
                            '/' -> Tile.MIRROR_FORWARD
                            '-' -> Tile.SPLITTER_HORIZONTAL
                            '|' -> Tile.SPLITTER_VERTICAL
                            else -> throw IllegalArgumentException("Unknown tile $c")
                        }, x = x, y = y
                    )
                }
            }
            return map
        }
    }
}
