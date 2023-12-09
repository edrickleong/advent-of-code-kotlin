package year2021.day12

import readInput

fun main() {
    val input = readInput("year2021/day12/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Number {
    val adjacencyMatrix = mutableMapOf<String, List<String>>()
    input.forEach {
        val (nodeA, nodeB) = it.split("-")
        adjacencyMatrix.merge(nodeA, listOf(nodeB)) { a, b -> a + b }
        adjacencyMatrix.merge(nodeB, listOf(nodeA)) { a, b -> a + b }
    }

    fun dfs(node: String, smallCavesVisited: MutableSet<String>): Int {
        if (node == "end") return 1
        if (smallCavesVisited.contains(node)) return 0

        if (node.first().isLowerCase()) smallCavesVisited.add(node)

        val nextNodes = adjacencyMatrix[node] ?: listOf()
        val numOfPaths = nextNodes.sumOf { dfs(it, smallCavesVisited) }
        smallCavesVisited.remove(node)
        return numOfPaths
    }

    return dfs("start", mutableSetOf())
}

private fun part2(input: List<String>): Number {
    val adjacencyMatrix = mutableMapOf<String, List<String>>()
    input.forEach {
        val (nodeA, nodeB) = it.split("-")
        adjacencyMatrix.merge(nodeA, listOf(nodeB)) { a, b -> a + b }
        adjacencyMatrix.merge(nodeB, listOf(nodeA)) { a, b -> a + b }
    }

    fun dfs(node: String, visitCountByNode: MutableMap<String, Int>): Int {
        if (node == "end") return 1
        if (visitCountByNode[node] == 2) return 0
        if (visitCountByNode[node] == 1 && (visitCountByNode.values.contains(2) || node == "start")) return 0

        if (node.first().isLowerCase()) visitCountByNode.merge(node, 1) { a, b -> a + b }

        val nextNodes = adjacencyMatrix[node] ?: listOf()
        val numOfPaths = nextNodes.sumOf { dfs(it, visitCountByNode) }
        visitCountByNode.merge(node, -1) { a, b -> a + b}
        return numOfPaths
    }

    return dfs("start", mutableMapOf())
}
