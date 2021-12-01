fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }
            .windowed(2)
            .count { (a, b) -> b > a }
    }

    fun part2(input: List<String>): Int {
        // When comparing two consecutive sliding windows, such as (n, n+1, n+2) and (n+1, n+2, n+3)
        // Then you can just check if n+3 > n in a window of 4 since n+1 and n+2 are shared between the two windows
        return input.map { it.toInt() }
            .windowed(4)
            .count { it.last() > it.first() }
    }

    val input = readInput("day01/input")
    println(part1(input))
    println(part2(input))
}
