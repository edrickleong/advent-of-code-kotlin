package day08

import readInput
import java.lang.IllegalStateException

fun main() {
    val input = readInput("day08/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var count = 0
    for (line in input) {
        val values = line.split("|").let { (_, second) ->
            second.separateByWhitespace().map { it.toSet() }
        }

        for (value in values) {
            when (value.size) {
                2, 3, 4, 7 -> count++
                else -> {}
            }
        }
    }
    return count
}


private fun part2(input: List<String>): Int {
    var sum = 0
    for (line in input) {
        val (signals, values) = line.split("|").let { (first, second) ->
            first.separateByWhitespace().map { it.toSet() } to
                    second.separateByWhitespace().map { it.toSet() }
        }

        val wireToSegment = generateMapOfWireToSegment(signals)
        val numbers = values.map { wires ->
            wires.map { wireToSegment[it]!! }.toSet().toNumber()
        }
        sum += numbers.joinToString(separator = "").toInt()
    }
    return sum
}

//  aa
// b  c
//  dd
// e  f
//  gg
private fun generateMapOfWireToSegment(signals: List<Set<Char>>): Map<Char, Char> {
    val map = mutableMapOf<Char, Char>()

    val cf = signals.single { it.size == 2 } // 1
    val acf = signals.single { it.size == 3 } // 7
    val bcdf = signals.single { it.size == 4 } // 4
    val fives = signals.filter { it.size == 5 } // [acdeg = 2, acdfg = 3, abdfg = 5] Intersect = adg
    val sixths = signals.filter { it.size == 6 } // [abcefg = 0, abdefg = 6, abcdfg = 9] Intersect = abfg

    val adg = fives.reduce { acc, set -> acc.intersect(set) }
    val abfg = sixths.reduce { acc, set -> acc.intersect(set) }
    val bd = bcdf.subtract(cf)

    val a = acf.subtract(cf).single().also { map[it] = 'a' }
    val f = cf.intersect(abfg).single().also { map[it] = 'f' }
    val c = cf.subtract(setOf(f)).single().also { map[it] = 'c' }
    val b = bd.intersect(abfg).single().also { map[it] = 'b' }
    val d = bd.subtract(setOf(b)).single().also { map[it] = 'd' }
    val g = adg.subtract(setOf(a, d)).single().also { map[it] = 'g' }
    val e = "abcdefg".toSet().subtract(setOf(a, b, c, d, f, g)).single().also { map[it] = 'e' }

    return map
}


//  aa
// b  c
//  dd
// e  f
//  gg
private fun Set<Char>.toNumber(): Int {
    return when (sorted().joinToString(separator = "")) {
        "abcefg" -> 0
        "cf" -> 1
        "acdeg" -> 2
        "acdfg" -> 3
        "bcdf" -> 4
        "abdfg" -> 5
        "abdefg" -> 6
        "acf" -> 7
        "abcdefg" -> 8
        "abcdfg" -> 9
        else -> throw IllegalStateException()
    }
}

private fun String.separateByWhitespace(): List<String> = this.split(" ").map { it.trim() }.filter { it.isNotBlank() }