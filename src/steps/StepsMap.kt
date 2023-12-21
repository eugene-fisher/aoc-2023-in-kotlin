package steps

class StepsMap(
    val width: Int,
    val height: Int,
) {

    private val rocks = BooleanArray(width * height)

    private fun index(position: Position): Int? {
        return if (position.x in 0..<width && position.y in 0..<height) {
            position.x + position.y * width
        } else null
    }

    fun putRock(x: Int, y: Int) {
        rocks[x + width * y] = true
    }

    fun startFrom(start: Position, steps: Int): Set<Position> {
        val positions = hashSetOf(start)
        repeat(steps) {
            val next = positions.flatMap {
                listOfNotNull(
                    it.north.takeIf { position -> index(position)?.let { i -> !rocks[i] } ?: false },
                    it.south.takeIf { position -> index(position)?.let { i -> !rocks[i] } ?: false },
                    it.west.takeIf { position -> index(position)?.let { i -> !rocks[i] } ?: false },
                    it.east.takeIf { position -> index(position)?.let { i -> !rocks[i] } ?: false },
                )
            }
            positions.clear()
            positions.addAll(next)
        }
        return positions
    }

    data class Position(val x: Int, val y: Int) {
        override fun toString(): String = "($x,$y)"

        val north: Position get() = Position(x, y - 1)
        val south: Position get() = Position(x, y + 1)
        val west: Position get() = Position(x - 1, y)
        val east: Position get() = Position(x + 1, y)
    }
}