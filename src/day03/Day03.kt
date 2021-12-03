@file:Suppress("RedundantIf")

package day03

import readInput

fun main() {
    fun part1(lines: List<String>): Int {
        val values = lines.map { it.toBits() }
        val numOfBits = lines[0].length
        val gamma = mutableListOf<Boolean>()
        val epsilon = mutableListOf<Boolean>()
        for (i in 0 until numOfBits) {
            val (ones, zeroes) = values.map { it[i] }.partition { it }
            val mostCommon = ones.size >= zeroes.size
            val leastCommon = !mostCommon
            gamma.add(mostCommon)
            epsilon.add(leastCommon)
        }

        return gamma.toInt() * epsilon.toInt()
    }

    fun part2(lines: List<String>): Int {
        val values = lines.map { it.toBits() }
        val numOfBits = lines[0].length
        var oxygenRatings = values
        var co2Ratings = values
        for (i in 0 until numOfBits) {
            if (oxygenRatings.size > 1) {
                val (ones, zeroes) = oxygenRatings.map { it[i] }.partition { it }
                val mostCommon = if (ones.size >= zeroes.size) true else false
                oxygenRatings = oxygenRatings.filter { it[i] == mostCommon }
            }

            if (co2Ratings.size > 1) {
                val (ones, zeroes) = co2Ratings.map { it[i] }.partition { it }
                val leastCommon = if (ones.size >= zeroes.size) false else true
                co2Ratings = co2Ratings.filter { it[i] == leastCommon }
            }
        }

        return oxygenRatings[0].toInt() * co2Ratings[0].toInt()
    }

    val input = readInput("day03/input")
    println(part1(input))
    println(part2(input))
}

fun String.toBits(): List<Boolean> {
    return map { it == '1' }
}

fun List<Boolean>.toInt(): Int {
    val bitString = map { if (it) '1' else '0' }.joinToString(separator = "")
    return Integer.parseInt(bitString, 2)
}