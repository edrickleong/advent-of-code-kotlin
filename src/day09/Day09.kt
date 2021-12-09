package day09

import readInput

fun main() {
    val input = readInput("day09/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    val matrix = input.map { line -> line.toCharArray().map { it.digitToInt() } }

    var riskLevels = 0
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            val height = matrix[i][j]
            if (i != 0 && matrix[i - 1][j] <= height) continue
            if (i != matrix.lastIndex && matrix[i + 1][j] <= height) continue
            if (j != 0 && matrix[i][j - 1] <= height) continue
            if (j != matrix.lastIndex && matrix[i][j + 1] <= height) continue

            riskLevels += matrix[i][j] + 1
        }
    }
    return riskLevels
}


private fun part2(input: List<String>): Int {
    val matrix = input.map { line -> line.toCharArray().map { it.digitToInt() } }

    fun getBasinSize(i: Int, j: Int, visited: MutableList<Pair<Int, Int>>, prevHeight: Int? = null): Int {
        if (i !in input.indices) return 0
        if (j !in input[0].indices) return 0
        if (visited.contains(i to j)) return 0

        val value = matrix[i][j]
        if (value == 9) return 0
        if (prevHeight != null && value <= prevHeight) return 0

        visited.add(i to j)
        val left = getBasinSize(i - 1, j, visited, value)
        val right = getBasinSize(i + 1, j, visited, value)
        val top = getBasinSize(i, j - 1, visited, value)
        val bottom = getBasinSize(i, j + 1, visited, value)
        return left + right + top + bottom + 1
    }

    val basinSizes = mutableListOf<Int>()
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            val height = matrix[i][j]
            if (i != 0 && matrix[i - 1][j] <= height) continue
            if (i != matrix.lastIndex && matrix[i + 1][j] <= height) continue
            if (j != 0 && matrix[i][j - 1] <= height) continue
            if (j != matrix.lastIndex && matrix[i][j + 1] <= height) continue

            basinSizes.add(getBasinSize(i, j, mutableListOf()))
        }
    }

    return basinSizes.sortedByDescending { it }.take(3).reduce { acc, size -> acc * size }
}
