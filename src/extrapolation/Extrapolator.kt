package extrapolation

class Extrapolator {

    fun nextValue(sequence: List<Int>): Int = if (sequence.all { it == 0 }) 0 else {
        sequence.last() + nextValue(calculateDiff(sequence))
    }

    fun previousValue(sequence: List<Int>): Int = if (sequence.all { it == 0 }) 0 else {
        sequence.first() - previousValue(calculateDiff(sequence))
    }

    fun calculateDiff(sequence: List<Int>): List<Int> {
        return sequence.zipWithNext { a, b -> b - a }
    }
}