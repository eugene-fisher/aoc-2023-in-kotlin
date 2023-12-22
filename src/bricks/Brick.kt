package bricks

import geometry.Vector3

data class Brick(
    val name: String,
    val side1: Vector3,
    val side2: Vector3,
) {
    fun translate(x: Int = 0, y: Int = 0, z: Int = 0): Brick = copy(
        side1 = side1.translate(x, y, z),
        side2 = side2.translate(x, y, z),
    )

    val cubes: List<Vector3> = (side1.x..side2.x).flatMap { x ->
        (side1.y..side2.y).flatMap { y ->
            (side1.z..side2.z).map { z ->
                Vector3(x, y, z)
            }
        }
    }

    val bottom: List<Vector3> = (side1.x..side2.x).flatMap { x ->
        (side1.y..side2.y).map { y ->
            Vector3(x, y, side1.z)
        }
    }

    companion object {
        fun parse(line: String): Brick {
            val (side1, side2) = line.split('~')
            return Brick(name = line, parseSide(side1), parseSide(side2))
        }

        private fun parseSide(side: String): Vector3 {
            val (x, y, z) = side.split(',')
            return Vector3(x.toInt(), y.toInt(), z.toInt())
        }
    }
}