package navigation

class Node(
    val descriptor: Descriptor,
    val left: Descriptor,
    var right: Descriptor,
) {
    data class Descriptor(val name: String)

    companion object {

        private val nodeRegex = Regex("(.+) = \\((.+), (.+)\\)")

        fun parseNode(line: String): Node {
            val match = requireNotNull(nodeRegex.find(line))
            return Node(
                descriptor = Descriptor(name = match.groups[1]!!.value),
                left = Descriptor(name = match.groups[2]!!.value),
                right = Descriptor(name = match.groups[3]!!.value),
            )
        }
    }
}