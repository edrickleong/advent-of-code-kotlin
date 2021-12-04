package day04

import readInput

fun main() {
    val input = readInput("day04/input")

    val numbers = input[0].split(",").map { it.toInt() }
    val linesOfBoards = input.subList(2, input.lastIndex).windowed(5, 6)
    val boards = linesOfBoards.map { it.toBoard() }

    println(part1(numbers, boards))
    println(part2(numbers, boards))

}

private fun part1(numbers: List<Int>, boards: List<Board>): Int {
    for (number in numbers) {
        for (board in boards) {
            val finalScore = board.markNumberAndCheckIfWon(number)
            if (finalScore != null) return finalScore
        }
    }
    return 0
}

private fun part2(numbers: List<Int>, boards: List<Board>): Int {
    var lastFinalScore = 0
    for (number in numbers) {
        for (board in boards) {
            val finalScore = board.markNumberAndCheckIfWon(number)
            if (finalScore != null) lastFinalScore = finalScore
        }
    }
    return lastFinalScore
}

fun List<String>.toBoard(): Board {
    val rows = map { line ->
        line.split(" ").filter { it.isNotBlank() }.map { Cell(it.toInt()) }
    }
    return Board(rows)
}

data class Cell(val number: Int) {
    var marked: Boolean = false
}

data class Board(val rows: List<List<Cell>>) {
    private var hasWon = false
    private var finalScore = 0

    fun markNumberAndCheckIfWon(number: Int): Int? {
        if (hasWon) return null

        val (rowIndex, colIndex) = markNumber(number) ?: return null
        if (checkWholeRowMarked(rowIndex) || checkWholeColMarked(colIndex)) {
            hasWon = true
            finalScore = calculateFinalScore(number)
            return finalScore
        }

        return null
    }

    private fun markNumber(number: Int): Pair<Int, Int>? {
        for (i in rows.indices) {
            for (j in rows[i].indices) {
                val cell = rows[i][j]
                if (cell.number == number) {
                    cell.marked = true
                    return i to j
                }
            }
        }
        return null
    }

    private fun checkWholeRowMarked(rowIndex: Int): Boolean {
        for (j in rows[0].indices) if (!rows[rowIndex][j].marked) return false
        return true
    }

    private fun checkWholeColMarked(colIndex: Int): Boolean {
        for (i in rows.indices) if (!rows[i][colIndex].marked) return false
        return true
    }

    private fun calculateFinalScore(number: Int): Int {
        val sum = rows.sumOf { row -> row.sumOf { if (!it.marked) it.number else 0 } }
        return sum * number
    }
}