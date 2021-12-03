package day03

import readInput

fun main() {
    fun part1(lines: List<String>): Int {
        val numOfBits = lines[0].length
        var gamma = 0
        var epsilon = 0
        for (i in 0 until numOfBits) {
            val (ones, zeroes) = lines.map { it[i] }.partition { it == '1' }
            val shiftLeftBy = numOfBits - 1 - i
            gamma += if (ones.size >= zeroes.size) 1 shl shiftLeftBy else 0
            epsilon += if (ones.size < zeroes.size) 1 shl shiftLeftBy else 0
        }

        return gamma * epsilon
    }

    fun part2(lines: List<String>): Int {
        val numOfBits = lines[0].length
        var oxygenRatings = lines
        var co2Ratings = lines
        for (i in 0 until numOfBits) {
            if (oxygenRatings.size > 1) {
                val (ones, zeroes) = oxygenRatings.map { it[i] }.partition { it == '1' }
                val mostCommon = if (ones.size >= zeroes.size) '1' else '0'
                oxygenRatings = oxygenRatings.filter { it[i] == mostCommon }
            }

            if (co2Ratings.size > 1) {
                val (ones, zeroes) = co2Ratings.map { it[i] }.partition { it == '1' }
                val leastCommon = if (ones.size < zeroes.size) '1' else '0'
                co2Ratings = co2Ratings.filter { it[i] == leastCommon }
            }
        }

        return oxygenRatings[0].fromBitsToInt() * co2Ratings[0].fromBitsToInt()
    }

    val input = readInput("day03/input")
    println(part1(input))
    println(part2(input))
}

private fun String.fromBitsToInt(): Int = Integer.parseInt(this, 2)