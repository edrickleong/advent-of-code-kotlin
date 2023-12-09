package year2021.day11

import readInput

fun main() {
    val input = readInput("year2021/day11/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Number {
    val matrix = input.map { line -> line.map { it.digitToInt() }.toMutableList() }
    var flashCount = 0
    fun increaseEnergyLevel(i: Int, j: Int) {
        if (i !in matrix.indices) return
        if (j !in matrix[0].indices) return

        matrix[i][j] = matrix[i][j] + 1
        if (matrix[i][j] == 10) {
            flashCount++
            (i to j).forEachAdjacent { adjacentI, adjacentJ ->
                increaseEnergyLevel(adjacentI, adjacentJ)
            }
        }
    }

    for (step in 1..100) {
        matrix.forEachCellIndexed { i, j, _ -> increaseEnergyLevel(i, j) }
        matrix.forEachCellIndexed { i, j, cell -> if (cell >= 10) matrix[i][j] = 0 }
    }

    return flashCount
}

private fun part2(input: List<String>): Number {
    val matrix = input.map { line -> line.map { it.digitToInt() }.toMutableList() }
    fun increaseEnergyLevel(i: Int, j: Int) {
        if (i !in matrix.indices) return
        if (j !in matrix[0].indices) return

        matrix[i][j] = matrix[i][j] + 1
        if (matrix[i][j] == 10) {
            (i to j).forEachAdjacent { adjacentI, adjacentJ ->
                increaseEnergyLevel(adjacentI, adjacentJ)
            }
        }
    }

    var step = 1
    while (true) {
        matrix.forEachCellIndexed { i, j, _ -> increaseEnergyLevel(i, j) }
        val allFlashing = matrix.all { row -> row.all { it >= 10 } }
        if (allFlashing) return step
        matrix.forEachCellIndexed { i, j, cell -> if (cell >= 10) matrix[i][j] = 0 }
        step++
    }
}

fun <T> List<List<T>>.forEachCellIndexed(action: (i: Int, j: Int, cell: T) -> Unit) {
    for (i in this.indices) {
        for (j in this[i].indices) {
            action(i, j, this[i][j])
        }
    }
}

fun Pair<Int, Int>.forEachAdjacent(action: (i: Int, j: Int) -> Unit) {
    for (i in first - 1..first + 1) {
        for (j in second - 1..second + 1) {
            if (i == first && j == second) continue
            action(i, j)
        }
    }
}
