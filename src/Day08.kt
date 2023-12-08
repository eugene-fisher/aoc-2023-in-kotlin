import navigation.Navigator
import navigation.Node
import navigation.NodeMap

fun main() {
    fun part1(input: List<String>): Int {
        val navigator = Navigator(
            move = Navigator.parseMove(input.first()),
            map = NodeMap(nodes = input.drop(2).map { Node.parseNode(it) })
        )
        return navigator.routeLength(from = Node.Descriptor("AAA"), to = Node.Descriptor("ZZZ"))
    }

    fun part2(input: List<String>): Long {
        val nodes = input.drop(2).map { Node.parseNode(it) }
        val navigator = Navigator(
            move = Navigator.parseMove(input.first()),
            map = NodeMap(nodes)
        )
        return navigator.navigateUntil(from = nodes.map { it.descriptor }.filter { it.name.endsWith("A") }) { list ->
            list.map { it.name.endsWith("Z") }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part2(testInput) == 6L)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
