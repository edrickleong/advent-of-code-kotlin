package day05

import readInput
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    val input = readInput("day05/input")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    val rows = MutableList(1000) {
        MutableList(1000) { 0 }
    }

    for (line in input) {
        val (p1, p2) = line.split("->").map { it.trim() }
        val (x1, y1) = p1.split(",").let { it[0].toInt() to it[1].toInt() }
        val (x2, y2) = p2.split(",").let { it[0].toInt() to it[1].toInt() }

        if (x1 == x2) {
            for (j in (y1..y2).ordered()) {
                rows[x1][j] = rows[x1][j] + 1
            }
            continue
        }

        if (y1 == y2) {
            for (i in (x1..x2).ordered()) {
                rows[i][y1] = rows[i][y1] + 1
            }
            continue
        }
    }

    val result = rows.sumOf { row ->
        row.count { it >= 2 }
    }
    return result
}

private fun part2(input: List<String>): Int {
    val rows = MutableList(1000) {
        MutableList(1000) { 0 }
    }

    for (line in input) {
        val (p1, p2) = line.split("->").map { it.trim() }
        val (x1, y1) = p1.split(",").let { it[0].toInt() to it[1].toInt() }
        val (x2, y2) = p2.split(",").let { it[0].toInt() to it[1].toInt() }

        if (x1 == x2) {
            for (j in (y1..y2).ordered()) {
                rows[x1][j] = rows[x1][j] + 1
            }
            continue
        }

        if (y1 == y2) {
            for (i in (x1..x2).ordered()) {
                rows[i][y1] = rows[i][y1] + 1
            }
            continue
        }

        if (x2 - x1 == y2 - y1 || x2 - x1 == -(y2 - y1)) {
            val num = (x2 - x1).absoluteValue
            for (i in 0..num) {
                val x = x1 + i * (x2 - x1).sign
                val y = y1 + i * (y2 - y1).sign
                rows[x][y] = rows[x][y] + 1
            }
        }
    }

    val result = rows.sumOf { row ->
        row.count { it >= 2 }
    }
    return result
}

fun IntProgression.ordered(): IntProgression {
    return if (first > last) last..first else this
}
