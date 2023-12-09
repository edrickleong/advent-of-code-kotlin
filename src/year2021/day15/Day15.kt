package year2021.day15

import readInput
import java.util.*

fun main() {
    val input = readInput("year2021/day15/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Number {
    val matrix = input.map { line -> line.map { it.digitToInt() } }
    return getShortestTotalRisk(matrix)
}

private fun part2(input: List<String>): Number {
    val matrix = input.map { line -> line.map { it.digitToInt() } }
    return getShortestTotalRisk(multipleMatrix(matrix))
}

private fun getShortestTotalRisk(matrix: List<List<Int>>): Number {
    val lowestRiskLevels = MutableList(matrix.size) { MutableList<Int?>(matrix[0].size) { null } }

    val paths = PriorityQueue<Path>(compareBy { it.cost })
    paths.add(Path(0, 0, 0))

    fun addPath(x: Int, y: Int, prevTotalRisk: Int) {
        if (x !in matrix.indices) return
        if (y !in matrix[0].indices) return
        if (lowestRiskLevels[x][y] != null) return

        paths.add(Path(x, y, prevTotalRisk + matrix[x][y]))
    }

    while (paths.isNotEmpty()) {
        val (x, y, currentTotalRisk) = paths.poll()
        if (lowestRiskLevels[x][y] != null) continue

        lowestRiskLevels[x][y] = currentTotalRisk
        if (x == matrix.lastIndex && y == matrix[0].lastIndex) return currentTotalRisk

        addPath(x - 1, y, currentTotalRisk)
        addPath(x + 1, y, currentTotalRisk)
        addPath(x, y - 1, currentTotalRisk)
        addPath(x, y + 1, currentTotalRisk)
    }

    return -1
}

data class Path(
    val x: Int,
    val y: Int,
    val cost: Int
)

fun multipleMatrix(matrix: List<List<Int>>): MutableList<MutableList<Int>> {
    val newMatrix = MutableList(matrix.size * 5) { MutableList(matrix[0].size * 5) { 0 } }
    repeat(5) { matrixI ->
        repeat(5) { matrixJ ->
            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    val total = matrix[i][j] + matrixI + matrixJ
                    newMatrix[matrixI * matrix.size + i][matrixJ * matrix[0].size + j] =
                        if (total < 10) total else total % 9
                }
            }
        }
    }
    return newMatrix
}
