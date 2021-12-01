fun main() {
    fun part1(input: List<String>): Int {
        val nums = input.map { it.toInt() }
        var result = 0
        for (i in nums.indices) {
            if (i == 0) continue
            if (nums[i] > nums[i - 1]) result++
        }

        return result
    }

    fun part2(input: List<String>): Int {
        val nums = input.map { it.toInt() }
        val slidingWindow = mutableListOf<Int>()
        for (i in 2..nums.lastIndex) {
            slidingWindow.add(nums[i - 2] + nums[i - 1] + nums[i])
        }

        var result = 0
        for (i in slidingWindow.indices) {
            if (i == 0) continue
            if (slidingWindow[i] > slidingWindow[i - 1]) result++
        }
        return result
    }

    val input = readInput("day01/input")
    println(part1(input))
    println(part2(input))
}
