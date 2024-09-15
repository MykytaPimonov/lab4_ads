package org.umsf.labs.lab4

public fun main() {
    println("Enter amount of nodes:")
    val len = readln().toInt()

    val tree = BinaryTree<Int>()
    for (i in 1..len) {
        println("Enter node value:")
        val value = readln().toInt()
        tree.add(value)
    }

    tree.print()
    println(tree.traverseDirectOrder())
    solve(tree)
    tree.print()
}

private fun solve(tree: BinaryTree<Int>) {
    val nodes = mutableMapOf<Int, Int>()
    tree.traverseDirectOrder().forEach { node ->
        if (nodes.containsKey(node)) {
            nodes[node] = nodes[node]!! + 1
        } else {
            nodes[node] = 1
        }
    }
    nodes.filter { it.value > 1 }
        .forEach { duplicate ->
            for (i in 1..duplicate.value) {
                tree.remove(duplicate.key)
            }
        }
}
