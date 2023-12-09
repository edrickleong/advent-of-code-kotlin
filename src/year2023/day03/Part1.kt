package year2023.day03

import readInput

fun main() {
    val input = readInput("year2023/day03/input")
    val result = part1(input)
    println(result)
}


fun part1(input: List<String>): Int {
    val pointToNumberIndex = mutableMapOf<Pair<Int, Int>, Int>()
    val numbers = mutableListOf<Int>()
    val seenIndexes = mutableSetOf<Int>()
    var currentNumberIndex = 0
    var currentNumber = ""
    for ((i, row) in input.withIndex()) {
        for ((j, cell) in row.withIndex()) {
            if (cell.isDigit()) {
                currentNumber += cell
                pointToNumberIndex[i to j] = currentNumberIndex
                continue
            }

            if (currentNumber.isNotEmpty()) {
                val number = currentNumber.toInt()
                numbers.add(number)
                currentNumber = ""
                currentNumberIndex++
            }
        }

        if (currentNumber.isNotEmpty()) {
            val number = currentNumber.toInt()
            numbers.add(number)
            currentNumber = ""
            currentNumberIndex++
        }
    }

    fun addPointToSeen(i: Int, j: Int) {
        if (i < 0 || i >= input.size) return
        if (j < 0 || j >= input[i].length) return
        val index = pointToNumberIndex[i to j] ?: return
        seenIndexes += index
    }

    for ((i, row) in input.withIndex()) {
        for ((j, cell) in row.withIndex()) {
            if (cell.isDigit()) continue
            if (cell == '.') continue

            addPointToSeen(i - 1, j - 1)
            addPointToSeen(i, j - 1)
            addPointToSeen(i + 1, j - 1)
            addPointToSeen(i - 1, j)
            addPointToSeen(i + 1, j)
            addPointToSeen(i - 1, j + 1)
            addPointToSeen(i, j + 1)
            addPointToSeen(i + 1, j + 1)

        }
    }

    var result = 0
    for (seenIndex in seenIndexes) {
        result += numbers[seenIndex]
    }

    return result
}