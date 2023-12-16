import lenses.Lens

fun main() {
    fun part1(input: List<String>): Int {
        return input.flatMap { line -> line.split(',') }.sumOf { Lens(it).hashCode() }
    }

    fun part2(input: List<String>): Int {
        val boxes = Array<MutableList<Lens>>(256) { mutableListOf() }
        input.flatMap { line -> line.split(',') }.forEach { code ->
            val (label, operation) = code.split('=', '-')
            val lens = Lens(label)
            val box = boxes[lens.hashCode()]
            if (operation.isEmpty()) {
                box.remove(lens)
            } else {
                val existing = box.firstOrNull { it == lens }
                if (existing != null) {
                    existing.focalLength = operation.toInt()
                } else {
                    box.add(lens.apply { focalLength = operation.toInt() })
                }
            }
        }
        return boxes.foldIndexed(0) { box, acc, lenses ->
            acc + lenses.mapIndexed { slot, lens -> lens.focalLength * slot.inc() * box.inc() }.sum()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day15_test")
    check(part1(testInput) == 1320)
    check(part2(testInput) == 145)

    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()
}
