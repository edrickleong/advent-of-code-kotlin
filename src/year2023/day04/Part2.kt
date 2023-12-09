package year2023.day04

import java.io.File

fun main() {
    val input = File("src/year2023/day04/input.txt").readLines()
    val result = part2(input)
    println(result)
}

fun part2(input: List<String>): Int {
    var scratchcards = 0
    val cardNumberToCopies = mutableMapOf<Int, Int>().withDefault { 1 }
    for ((index, line) in input.withIndex()) {
        val cardNumber = index + 1
        val numbers = line.substringAfter(":").trim()
        val (winningNumbers, yourNumbers) = numbers.split("|")
        val winningNumbersList = winningNumbers.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }.toSet()
        val yourNumbersList = yourNumbers.trim().split(" ").filter { it.isNotEmpty() }.map { it.toInt() }
        var numberOfMatches = 0
        for (number in yourNumbersList) {
            if (number in winningNumbersList) {
                numberOfMatches++
            }
        }
        val copies = cardNumberToCopies.getValue(cardNumber)
        scratchcards += copies
        for (offset in 1..<1 + numberOfMatches) {
            if (cardNumber + offset > input.size) break
            val currentCopies = cardNumberToCopies.getValue(cardNumber + offset)
            cardNumberToCopies[cardNumber + offset] = currentCopies + copies
        }
    }
    return scratchcards
}