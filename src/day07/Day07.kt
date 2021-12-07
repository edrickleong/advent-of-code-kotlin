package day07

import readInput
import kotlin.math.abs

fun main() {
    val input = readInput("day07/input")
    val positions = input[0].split(",").map { it.toInt() }

    val min = positions.minOf { it }
    val max = positions.maxOf { it }

    println(part1(min, max, positions))
    println(part2(min, max, positions))
}

private fun part1(min: Int, max: Int, positions: List<Int>): Long {
    var result = Long.MAX_VALUE
    for (i in min..max) {
        result = minOf(result, positions.sumOf { abs(it - i).toLong() })
    }

    return result
}

private fun part2(min: Int, max: Int, positions: List<Int>): Long {
    var result = Long.MAX_VALUE
    for (i in min..max) {
        result = minOf(result, positions.sumOf { fuelCost(abs(it - i)) })
    }

    return result
}

val memo = mutableMapOf<Int, Long>()
fun fuelCost(distance: Int): Long {
    if (distance == 0) return 0
    memo[distance]?.let { return it }
    val result = distance + fuelCost(distance - 1)
    memo[distance] = result
    return result
}
