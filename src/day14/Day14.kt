package day14

import readInput

fun main() {
    val input = readInput("day14/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Number {
    var countPerPair = input[0].windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    val pairToNewPairs = input.subList(2, input.size).associate {
        val (pair, char) = it.split(" -> ")
        pair to listOf(pair[0] + char, char + pair[1])
    }

    repeat(10) {
        val newCountPerPair = mutableMapOf<String, Long>()
        countPerPair.forEach { entry ->
            val newPairs = pairToNewPairs[entry.key] ?: listOf(entry.key)
            newPairs.forEach {
                newCountPerPair.merge(it, entry.value) { a, b -> a + b }
            }
        }
        countPerPair = newCountPerPair
    }

    val countPerChar = mutableMapOf<Char, Long>()
    countPerPair.forEach {
        countPerChar.merge(it.key[0], it.value) { a, b -> a + b }
    }
    countPerChar.merge(input[0].last(), 1) { a, b -> a + b }

    return countPerChar.maxOf { it.value } - countPerChar.minOf { it.value }
}

private fun part2(input: List<String>): Number {
    var countPerPair = input[0].windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    val pairToNewPairs = input.subList(2, input.size).associate {
        val (pair, char) = it.split(" -> ")
        pair to listOf(pair[0] + char, char + pair[1])
    }

    repeat(40) {
        val newCountPerPair = mutableMapOf<String, Long>()
        countPerPair.forEach { entry ->
            val newPairs = pairToNewPairs[entry.key] ?: listOf(entry.key)
            newPairs.forEach {
                newCountPerPair.merge(it, entry.value) { a, b -> a + b }
            }
        }
        countPerPair = newCountPerPair
    }

    val countPerChar = mutableMapOf<Char, Long>()
    countPerPair.forEach {
        countPerChar.merge(it.key[0], it.value) { a, b -> a + b }
    }
    countPerChar.merge(input[0].last(), 1) { a, b -> a + b }

    return countPerChar.maxOf { it.value } - countPerChar.minOf { it.value }
}
