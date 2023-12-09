package year2023.day04

import readInput

fun main() {
    val input = readInput("year2023/day04/input")
    val result = part1(input)
    println(result)
}

fun part1(input: List<String>): Int {
    var result = 0
    for (line in input) {
        val numbers = line.substringAfter(":").trim()
        val (winningNumbers, yourNumbers) = numbers.split("|")
        val winningNumbersList = winningNumbers.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }.toSet()
        val yourNumbersList = yourNumbers.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        var cardPoints = 0
        for (number in yourNumbersList) {
            if (number in winningNumbersList) {
                cardPoints = if (cardPoints == 0) 1 else cardPoints * 2
            }
        }
        result += cardPoints
    }
    return result
}