import reflections.ReflectedImageParser
import reflections.ReflectionProcessor

fun main() {
    fun part1(input: List<String>): Int {
        val parser = ReflectedImageParser()
        input.forEach { parser.addLine(it) }
        parser.finish()

        val processor = ReflectionProcessor()
        return parser.images.sumOf { image ->
            val horizontalLines = processor.findReflectionRow(image) ?: 0
            val verticalLines = processor.findReflectionColumn(image) ?: 0
            horizontalLines * 100 + verticalLines
        }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 405)

    val input = readInput("Day13")
    part1(input).println()
    part2(input).println()
}
