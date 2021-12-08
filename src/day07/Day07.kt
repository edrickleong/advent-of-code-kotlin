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
    return (min..max).minOf { position ->
        positions.sumOf { abs(it - position).toLong() }
    }
}

private fun part2(min: Int, max: Int, positions: List<Int>): Long {
    return (min..max).minOf { position ->
        positions.sumOf { fuelCost(abs(it - position)) }
    }
}

val memo = mutableMapOf<Int, Long>()
fun fuelCost(distance: Int): Long {
    if (distance == 0) return 0
    return memo.getOrPut(distance) { distance + fuelCost(distance - 1) }
}
