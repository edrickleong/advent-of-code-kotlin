package year2023.day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = line.first { it.isDigit() }
            val lastDigit = line.last { it.isDigit() }
            val combinedDigits = "$firstDigit$lastDigit".toInt()
            combinedDigits
        }
    }

    fun part2(input: List<String>): Int {
        val numbers = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
        )
        return input.sumOf { line ->
            val firstNumber = line.findAnyOf(numbers.keys)?.second ?: return@sumOf 0
            val lastNumber = line.findLastAnyOf(numbers.keys)?.second ?: return@sumOf 0
            val firstNumberValue = numbers[firstNumber]!!
            val lastNumberValue = numbers[lastNumber]!!
            val combinedNumbers = "$firstNumberValue$lastNumberValue".toInt()
            combinedNumbers
        }
    }

    val input = readInput("year2023/day01/input")
    println(part1(input))
    println(part2(input))
}


