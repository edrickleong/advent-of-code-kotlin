package year2023.day02

import readInput

fun main() {
    val input = readInput("year2023/day02/input")
    val result = part1(input)
    println(result)
}

fun part1(input: List<String>): Int {
    var sumOfGameIds = 0
    line@for (line in input) {
        val (game, rest) = line.split(":")
        val gameId = game.substringAfter("Game ")
        val draws = rest.split(";")
        for (draw in draws) {
            val colors = draw.split(",")
            for (color in colors) {
                val (number, color) = color.trim().split(" ")
                if (color == "red" && number.toInt() > 12) continue@line
                if (color == "green" && number.toInt() > 13) continue@line
                if (color == "blue" && number.toInt() > 14) continue@line
            }
        }
        sumOfGameIds += gameId.toInt()
    }
    return sumOfGameIds
}