package maze

import java.lang.IllegalArgumentException


sealed class MazeTile(val shape: Char) {

    override fun toString(): String = shape.toString()

    object Ground : MazeTile(shape = '.')

    object Terminator : MazeTile(shape = 'S')

    class Pipe(
        shape: Char,
        val side1: Connector,
        val side2: Connector,
    ) : MazeTile(shape) {

        class Connector(val relativePosition: Position)
    }

    data class Position(val x: Int, val y: Int) {
        fun translated(dx: Int, dy: Int): Position = Position(x - dx, y - dy)
        fun translated(by: Position) = translated(dx = -by.x, dy = -by.y)
    }

    companion object {
        fun parse(tile: Char): MazeTile = when (tile) {
            '.' -> Ground
            '-' -> Pipe(tile, Pipe.Connector(Position(-1, 0)), Pipe.Connector(Position(1, 0)))
            '|' -> Pipe(tile, Pipe.Connector(Position(0, -1)), Pipe.Connector(Position(0, 1)))
            'L' -> Pipe(tile, Pipe.Connector(Position(0, -1)), Pipe.Connector(Position(1, 0)))
            'F' -> Pipe(tile, Pipe.Connector(Position(1, 0)), Pipe.Connector(Position(0, 1)))
            '7' -> Pipe(tile, Pipe.Connector(Position(-1, 0)), Pipe.Connector(Position(0, 1)))
            'J' -> Pipe(tile, Pipe.Connector(Position(-1, 0)), Pipe.Connector(Position(0, -1)))
            'S' -> Terminator
            else -> throw IllegalArgumentException("Tile $tile is not supported")
        }
    }
}