package day10

import readInput
import java.util.*

fun main() {
    val input = readInput("day10/input")

    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Number {
    return input.sumOf { calculateSyntaxErrorScore(it) }
}

private fun calculateSyntaxErrorScore(line: String): Int {
    val stack = LinkedList<Char>()
    for (c in line) {
        when (c) {
            '(', '[', '{', '<' -> stack.push(c)
            ')' -> if (stack.pollFirst() != '(') return 3
            ']' -> if (stack.pollFirst() != '[') return 57
            '>' -> if (stack.pollFirst() != '<') return 25137
            '}' -> if (stack.pollFirst() != '{') return 1197
        }
    }
    return 0
}

private fun part2(input: List<String>): Number {
    val scores = input.mapNotNull { calculateAutocompleteScore(it) }
    return scores.sorted()[scores.size / 2]
}

private fun calculateAutocompleteScore(line: String): Long? {
    val stack = LinkedList<Char>()
    for (c in line) {
        when (c) {
            '(', '[', '{', '<' -> stack.push(c)
            ')' -> if (stack.pollFirst() != '(') return null
            ']' -> if (stack.pollFirst() != '[') return null
            '>' -> if (stack.pollFirst() != '<') return null
            '}' -> if (stack.pollFirst() != '{') return null
        }
    }

    var score = 0L
    while (stack.isNotEmpty()) {
        when (stack.pollFirst()) {
            '(' -> score = score * 5 + 1
            '[' -> score = score * 5 + 2
            '{' -> score = score * 5 + 3
            '<' -> score = score * 5 + 4
        }
    }
    return score
}