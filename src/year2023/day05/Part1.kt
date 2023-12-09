package year2023.day05

import splitByEmptyLines
import java.io.File

fun main() {
    val input = File("src/year2023/day05/input.txt").readLines()
    val result = part1(input)
    println(result)
}

fun part1(input: List<String>): Long {
    val blocks = input.splitByEmptyLines()
    val seedsBlock = blocks[0]
    val seeds = seedsBlock[0]
        .substringAfter(":")
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.toLong() }

    var numbers = seeds
    val mapBlock = blocks.drop(1)
    for (mapLines in mapBlock) {
        val resultOfMapLines = mapLines.drop(1)
        val maps = mutableListOf<Triple<Long, Long, Long>>()
        for (mapLine in resultOfMapLines) {
            val (source, destination, range) = mapLine.split(" ").map { it.toLong() }
            maps.add(Triple(source, destination, range))
        }
        val newResult = mutableListOf<Long>()
        number@ for (number in numbers) {
            for (map in maps) {
                if (number in map.second..<map.second + map.third) {
                    newResult.add(map.first + (number - map.second))
                    continue@number
                }
            }
            newResult.add(number)
        }
        numbers = newResult
    }
    return numbers.min()
}