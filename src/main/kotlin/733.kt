package org.mdu

import kotlin.time.measureTimedValue

//https://leetcode.com/problems/flood-fill/description/
class Solution {

    fun floodFillDfs(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val original = image[sr][sc]
        val rowCount = image.size
        val colCount = image[0].size

        fun dsf(row: Int, col: Int) {
            if (row < 0 || row >= rowCount || col < 0 || col >= colCount || image[row][col] != original || image[row][col] == color) {
                return
            }

            image[row][col] = color

            dsf(row - 1, col)
            dsf(row + 1, col)
            dsf(row, col - 1)
            dsf(row, col + 1)
        }

        dsf(sr, sc)
        return image
    }


    fun floodFillIterative(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        val original = image[sr][sc]

        if (original == color) {
            return image
        }

        val rowCount = image.size
        val colCount = image[0].size
        val queue = ArrayDeque<Pair<Int, Int>>()
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

        queue.add(sr to sc)

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeLast()
            val current = image[x][y]

            if (current == original) {
                image[x][y] = color

                for ((dx, dy) in directions) {
                    val newRow = dx + x
                    val newCol = dy + y

                    if (newRow in 0 until rowCount && newCol in 0 until colCount) {
                        queue.add(newRow to newCol)
                    }
                }
            }
        }

        return image
    }
}


fun main() {
    val solution = Solution()

    val input1 = { arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1)).copyOf() }
    val expected1 = arrayOf(intArrayOf(2, 2, 2), intArrayOf(2, 2, 0), intArrayOf(2, 0, 1))

    execute(expected1, "floodFillDfs") {
        solution.floodFillDfs(input1(), 1, 1, 2)
    }

    execute(expected1, "floodFillIterative") {
        solution.floodFillIterative(input1(), 1, 1, 2)
    }
}

fun execute(expected: Array<IntArray>, message: String, operation: () -> Array<IntArray>) =
    measureTimedValue {
        operation()
    }.also {
        it.duration.also { println("Execution time ${it.inWholeMilliseconds} [ms] of $message") }
        it.value.joinToString("\n") { cell -> cell.joinToString(",") }.also(::println)
    }.also {
        assert(expected.contentEquals(it.value))
    }
