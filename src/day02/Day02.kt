package day02

import readInput

fun main() {
    fun part1(lines: List<String>): Int {
        var depth = 0
        var position = 0
        for (line in lines) {
            val (direction, num) = line.split(" ").let { it[0] to it[1].toInt() }
            when (direction) {
                "up" -> depth -= num
                "down" -> depth += num
                "forward" -> position += num
            }
        }

        return position * depth
    }

    fun part2(lines: List<String>): Int {
        var aim = 0
        var depth = 0
        var position = 0
        for (line in lines) {
            val (direction, num) = line.split(" ").let { it[0] to it[1].toInt() }
            when (direction) {
                "up" -> aim -= num
                "down" -> aim += num
                "forward" -> {
                    position += num
                    depth += (num * aim)
                }
            }
        }
        return position * depth
    }

    val input = readInput("day02/input")
    println(part1(input))
    println(part2(input))
}
