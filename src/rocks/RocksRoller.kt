package rocks

class RocksRoller(
    private val platform: RocksPlatform,
) {

    fun runCycles(times: Int): Int {
        val hashByCycle = mutableListOf<Int>()
        repeat(times) {
            cycle()
            hashByCycle.add(platform.toString().hashCode() * platform.calculateLoadNorth())
            val repeat = findRepeats(hashByCycle)
            if (repeat != null) {
                val stepsToTarget = (times - repeat.from - 1) % repeat.length
                repeat(times = stepsToTarget) {
                    cycle()
                }
                return platform.calculateLoadNorth()
            }
        }
        return platform.calculateLoadNorth()
    }

    private fun cycle() {
        platform.tiltNorth()
        platform.tiltWest()
        platform.tiltSouth()
        platform.tiltEast()
    }

    private fun findRepeats(list: List<Int>): RepeatResult? {
        val firstIndex = list.take(list.size - 1).indexOf(list.last())
        if (firstIndex != -1) {
            return RepeatResult(from = firstIndex, length = list.size - 1 - firstIndex)
        }
        return null
    }

    private data class RepeatResult(val from: Int, val length: Int)
}