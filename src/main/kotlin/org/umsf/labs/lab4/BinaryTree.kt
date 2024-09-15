package org.umsf.labs.lab4

internal class BinaryTree<T : Comparable<T>> {

    private var root: Node<T>? = null

    private val isEmpty: Boolean
        get() = root == null

    internal fun add(value: T) {
        root = addRecursive(root, value)
    }

    private fun addRecursive(current: Node<T>?, value: T): Node<T> {
        if (current == null) {
            return Node(value)
        }
        if (value <= current.value()) {
            current.changeLeft(addRecursive(current.leftNode(), value))
        } else if (value > current.value()) {
            current.changeRight(addRecursive(current.rightNode(), value))
        }
        return current
    }

    internal fun remove(value: T) {
        root = removeRecursive(root, value)
        println()
    }

    private fun removeRecursive(current: Node<T>?, value: T): Node<T>? {
        if (current == null) {
            return null
        }
        if (value == current.value()) {
            if (current.leftNode() == null && current.rightNode() == null) {
                return null
            }
            if (current.leftNode() == null) {
                return current.rightNode()
            }
            if (current.rightNode() == null) {
                return current.leftNode()
            }

            val smallestValue = smallestValue(current.rightNode()!!)
            current.changeValue(smallestValue)
            current.changeRight(removeRecursive(current.rightNode(), smallestValue))
            return current
        }

        if (value < current.value()) {
            current.changeLeft(removeRecursive(current.leftNode(), value))
        } else {
            current.changeRight(removeRecursive(current.rightNode(), value))
        }

        return current
    }

    private fun smallestValue(root: Node<T>): T {
        val leftNode = root.leftNode()
        if (leftNode === null) return root.value()
        return smallestValue(leftNode)
    }

    internal fun traverseDirectOrder(): List<T> {
        val nodes = mutableListOf<T>()
        traverseDirectOrderRecursive(root, nodes)
        return nodes
    }

    private fun traverseDirectOrderRecursive(node: Node<T>?, nodes: MutableList<T>) {
        if (node != null) {
            nodes.add(node.value())
            traverseDirectOrderRecursive(node.leftNode(), nodes)
            traverseDirectOrderRecursive(node.rightNode(), nodes)
        }
    }

    internal fun print() {
        if (isEmpty) {
            println("Tree is empty")
        } else {
            printTreeRecursive(root, "", false)
        }
    }

    private fun printTreeRecursive(node: Node<T>?, prefix: String, isLeft: Boolean) {
        if (node == null) {
            return
        }
        println(prefix + (if (isLeft) "├── " else "└── ") + node.value())
        printTreeRecursive(node.leftNode(), prefix + if (isLeft) "│   " else "    ", true)
        printTreeRecursive(node.rightNode(), prefix + if (isLeft) "│   " else "    ", false)
    }


    internal class Node<T>(
        private var value: T,
        private var left: Node<T>? = null,
        private var right: Node<T>? = null
    ) {
        internal fun value() = value

        internal fun changeValue(newValue: T) {
            value = newValue
        }

        internal fun leftNode() = left

        internal fun changeLeft(node: Node<T>?) {
            left = node
        }

        internal fun rightNode() = right

        internal fun changeRight(node: Node<T>?) {
            right = node
        }
    }
}
