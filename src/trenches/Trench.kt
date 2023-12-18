package trenches

import java.awt.geom.Point2D

class Trench {

    private var x = 0.0
    private var y = 0.0
    private var perimeter = 0.0

    private val vertices = mutableListOf<Point2D.Double>()

    fun dig(direction: Direction, steps: Int) {
        perimeter += steps
        when (direction) {
            Direction.UP -> y -= steps
            Direction.DOWN -> y += steps
            Direction.LEFT -> x -= steps
            Direction.RIGHT -> x += steps

        }
        vertices.add(Point2D.Double(x, y))
    }

    val area: Double get() {
        val insideArea = vertices.zipWithNext { a, b -> a.x * b.y - b.x * a.y }.sum() / 2.0
        val outsideArea = perimeter / 2.0 + 1.0
        return insideArea + outsideArea
    }


    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }
}