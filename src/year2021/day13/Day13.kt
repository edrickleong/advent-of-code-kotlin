package year2021.day13

import readInput
import splitByEmptyLines

fun main() {
    val input = readInput("year2021/day13/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Number {
    var matrix = MutableList(2000) { MutableList(2000) { false } }
    val (coordinates, instructions) = input.splitByEmptyLines().let { (block1, block2) ->
        val coordinates = block1.map { line -> line.split(",").let { (x, y) -> x.toInt() to y.toInt() } }
        val instructions = block2.map { line ->
            line.removePrefix("fold along ").split("=").let { (axis, value) -> axis to value.toInt() }
        }
        coordinates to instructions
    }

    coordinates.forEach { (x, y) -> matrix[y][x] = true }

    for ((axis, value) in instructions.take(1)) {
        if (axis == "x") {
            for (i in matrix.indices) {
                for (j in value until (value * 2 + 1)) {
                    if (matrix[i][j]) matrix[i][value - (j - value)] = true
                }
                matrix[i] = matrix[i].subList(0, value)
            }
        } else {
            for (i in value until (value * 2 + 1)) {
                for (j in matrix[0].indices) {
                    if (matrix[i][j]) matrix[value - (i - value)][j] = true
                }
            }
            matrix = matrix.subList(0, value)
        }
    }

    return matrix.sumOf { row -> row.count { it } }
}

private fun part2(input: List<String>): Number {
    var matrix = MutableList(2000) { MutableList(2000) { false } }
    val (coordinates, instructions) = input.splitByEmptyLines().let { (block1, block2) ->
        val coordinates = block1.map { line -> line.split(",").let { (x, y) -> x.toInt() to y.toInt() } }
        val instructions = block2.map { line ->
            line.removePrefix("fold along ").split("=").let { (axis, value) -> axis to value.toInt() }
        }
        coordinates to instructions
    }

    coordinates.forEach { (x, y) -> matrix[y][x] = true }

    for ((axis, value) in instructions) {
        if (axis == "x") {
            for (i in matrix.indices) {
                for (j in value until (value * 2 + 1)) {
                    if (matrix[i][j]) matrix[i][value - (j - value)] = true
                }
                matrix[i] = matrix[i].subList(0, value)
            }
        } else {
            for (i in value until (value * 2 + 1)) {
                for (j in matrix[0].indices) {
                    if (matrix[i][j]) matrix[value - (i - value)][j] = true
                }
            }
            matrix = matrix.subList(0, value)
        }
    }

    println(matrix.joinToString("\n") { row -> row.joinToString("") { if (it) "#" else "." } })
    return 0
}

