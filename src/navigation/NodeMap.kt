package navigation

class NodeMap(nodes: Iterable<Node>) {

    private val descriptorToNode = hashMapOf<Node.Descriptor, Node>().apply {
        nodes.forEach { this[it.descriptor] = it }
    }

    fun get(descriptor: Node.Descriptor): Node = requireNotNull(descriptorToNode[descriptor])
}