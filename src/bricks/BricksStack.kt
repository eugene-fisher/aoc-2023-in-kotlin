package bricks

import geometry.Vector3

class BricksStack {

    private val landed = mutableListOf<Brick>()

    fun drop(brick: Brick) = dropTo(landed, brick)

    var safeToDisintegrate: Int = 0
        private set
    var totalDisintegrationImpact: Int = 0
        private set

    fun hypotheticalDisintegration() {
        landed.forEach { brickToRemove ->
            val allWithoutOne = landed - brickToRemove
            val newLanded = mutableListOf<Brick>()
            allWithoutOne.forEach { dropTo(newLanded, it) }

            if (allWithoutOne == newLanded) {
                safeToDisintegrate++
            } else {
                totalDisintegrationImpact += newLanded.count { it !in allWithoutOne }
            }
        }
    }

    private fun dropTo(list: MutableList<Brick>, brick: Brick) {
        val brickBottom = brick.bottom
        val highest = brickBottom.map { list.findHighestPosition(it) }.max()
        list.add(brick.translate(z = (highest - brickBottom.first().z).also { require(it <= 0) }))
    }

    private fun List<Brick>.findHighestPosition(from: Vector3): Int = lastOrNull { brick ->
        brick.cubes.any { it.x == from.x && it.y == from.y }
    }?.cubes?.maxOf { it.z + 1 } ?: 0
}