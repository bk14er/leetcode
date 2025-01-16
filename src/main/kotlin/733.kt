package org.mdu

import kotlin.time.measureTime


//https://leetcode.com/problems/flood-fill/description/
class Solution {

    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
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

    // Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
    // Output: [[2,2,2],[2,2,0],[2,0,1]]
    solution.floodFill(
        arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1)),
        1,
        1,
        2
    ).joinToString("\n") { cell -> cell.joinToString(",") }.also(::println)
}