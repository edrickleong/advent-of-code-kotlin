package year2023.day03

import readInput

fun main() {
    val input = readInput("year2023/day03/input")
    val result = part2(input)
    println(result)
}

fun part2(input: List<String>): Int {
    val pointToNumberIndex = mutableMapOf<Pair<Int, Int>, Int>()
    val numbers = mutableListOf<Int>()
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

    fun getPartNumberIndex(i: Int, j: Int): Int? {
        if (i < 0 || i >= input.size) return null
        if (j < 0 || j >= input[i].length) return null
        val index = pointToNumberIndex[i to j] ?: return null
        return index
    }

    var result = 0

    for ((i, row) in input.withIndex()) {
        for ((j, cell) in row.withIndex()) {
            if (cell.isDigit()) continue
            if (cell == '.') continue

            val partNumberIndexes = mutableSetOf<Int>()
            getPartNumberIndex(i - 1, j - 1)?.let { partNumberIndexes += it }
            getPartNumberIndex(i, j - 1)?.let { partNumberIndexes += it }
            getPartNumberIndex(i + 1, j - 1)?.let { partNumberIndexes += it }
            getPartNumberIndex(i - 1, j)?.let { partNumberIndexes += it }
            getPartNumberIndex(i + 1, j)?.let { partNumberIndexes += it }
            getPartNumberIndex(i - 1, j + 1)?.let { partNumberIndexes += it }
            getPartNumberIndex(i, j + 1)?.let { partNumberIndexes += it }
            getPartNumberIndex(i + 1, j + 1)?.let { partNumberIndexes += it }

            if (partNumberIndexes.size == 2) {
                val (first, second) = partNumberIndexes.toList()
                val firstNumber = numbers[first]
                val secondNumber = numbers[second]
                result += firstNumber * secondNumber
            }
        }
    }

    return result
}