import beams.BeamMap
import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        val map = BeamMap.parse(input)
        map.projectBeam(initial = BeamMap.Beam(x = -1, y = 0, vx = 1, vy = 0))
        return map.energizedTiles
    }

    fun part2(input: List<String>): Int {
        val map = BeamMap.parse(input)
        var max = 0
        (0..<map.height).forEach {
            map.projectBeam(initial = BeamMap.Beam(x = -1, y = it, vx = 1, vy = 0))
            max = max(max, map.energizedTiles)
            map.clearEnergized()
        }
        (0..<map.height).forEach {
            map.projectBeam(initial = BeamMap.Beam(x = map.width, y = it, vx = -1, vy = 0))
            max = max(max, map.energizedTiles)
            map.clearEnergized()
        }
        (0..<map.width).forEach {
            map.projectBeam(initial = BeamMap.Beam(x = it, y = -1, vx = 0, vy = 1))
            max = max(max, map.energizedTiles)
            map.clearEnergized()
        }
        (0..<map.width).forEach {
            map.projectBeam(initial = BeamMap.Beam(x = it, y = map.height, vx = 0, vy = -1))
            max = max(max, map.energizedTiles)
            map.clearEnergized()
        }
        return max
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day16_test")
    check(part1(testInput) == 46)
    check(part2(testInput) == 51)

    val input = readInput("Day16")
    part1(input).println()
    part2(input).println()
}
