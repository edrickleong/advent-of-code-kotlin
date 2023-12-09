import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun List<String>.splitByEmptyLines(): List<List<String>> {
    val blocks = mutableListOf<MutableList<String>>()
    var currentBlock = mutableListOf<String>()
    for (line in this) {
        when {
            line.isNotEmpty() -> currentBlock.add(line)
            else -> {
                if (currentBlock.isEmpty()) continue

                blocks.add(currentBlock)
                currentBlock = mutableListOf()
            }
        }
    }

    if (currentBlock.isNotEmpty()) blocks.add(currentBlock)

    return blocks
}