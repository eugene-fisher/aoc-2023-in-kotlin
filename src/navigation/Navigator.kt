package navigation

import java.lang.IllegalArgumentException

class Navigator(
    private val move: List<Step>,
    private val map: NodeMap,
) {

    fun routeLength(from: Node.Descriptor, to: Node.Descriptor): Int {
        var current = map.get(from)
        var travelled = 0
        while (current.descriptor != to) {
            current = map.get(
                when (move[travelled % move.size]) {
                    Step.LEFT -> current.left
                    Step.RIGHT -> current.right
                }
            )
            travelled++
        }
        return travelled
    }

    fun navigateUntil(from: List<Node.Descriptor>, finishSelector: (List<Node.Descriptor>) -> List<Boolean>): Long {
        var current = from.map { map.get(it) }
        var travelled = 0L
        val loopDistances = LongArray(from.size) { -1L }
        while (loopDistances.any { it == -1L }) {
            current = current.map { node ->
                map.get(
                    when (move[travelled.mod(move.size)]) {
                        Step.LEFT -> node.left
                        Step.RIGHT -> node.right
                    }
                )
            }
            travelled++

            val finishes = finishSelector(current.map { it.descriptor })
            for(i in loopDistances.indices) {
                if (finishes[i] && loopDistances[i] == -1L) {
                    loopDistances[i] = travelled
                }
            }
        }
        return lcm(loopDistances)
    }

    private fun lcm(numbers: LongArray) = numbers.reduce { acc, number -> lcm(acc, number) }

    private fun lcm(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm.mod(a) == 0L && lcm.mod(b) == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    enum class Step {
        LEFT, RIGHT
    }

    companion object {
        fun parseMove(line: String): List<Step> {
            return line.map {
                when(it) {
                    'L' -> Step.LEFT
                    'R' -> Step.RIGHT
                    else -> throw IllegalArgumentException("Direction $it is not supported")
                }
            }
        }
    }
}