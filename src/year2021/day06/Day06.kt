package year2021.day06

import readInput

fun main() {
    val input = readInput("year2021/day06/input")
    val startingTimers = input[0].split(",").map { it.toInt() }

    println(part1(startingTimers))
    println(part2(startingTimers))
}

fun part1(startingTimers: List<Int>): Long {
    return startingTimers.sumOf { calculateFishes(it, 80) }
}

fun part2(startingTimers: List<Int>): Long {
    return startingTimers.sumOf { calculateFishes(it, 256) }
}

val memo = mutableMapOf<Pair<Int, Int>, Long>()
fun calculateFishes(timer: Int, daysRemaining: Int): Long {
    if (daysRemaining < 0) return 0
    val nextDaysRemaining = daysRemaining - (timer + 1)
    if (nextDaysRemaining < 0) return 1

    memo[timer to daysRemaining]?.let { return@calculateFishes it }

    val result = calculateFishes(6, nextDaysRemaining) + calculateFishes(8, nextDaysRemaining)
    memo[timer to daysRemaining] = result
    return result
}
