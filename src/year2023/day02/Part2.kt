package year2023.day02

import readInput

fun main() {
    val input = readInput("year2023/day02/input")
    val result = part(input)
    println(result)
}

fun part(input: List<String>): Int {
    var result = 0
    line@ for (line in input) {
        val (_, rest) = line.split(":")
        val draws = rest.split(";")
        var red = 0
        var green = 0
        var blue = 0
        for (draw in draws) {
            val ballSets = draw.split(",")
            for (ballSet in ballSets) {
                val numberAndColor = ballSet.trim().split(" ")
                val number = numberAndColor[0].toInt()
                val color = numberAndColor[1]
                when (color) {
                    "red" -> red = maxOf(red, number)
                    "green" -> green = maxOf(green, number)
                    "blue" -> blue = maxOf(blue, number)
                }
            }
        }
        val power = red * green * blue
        result += power
    }
    return result
}