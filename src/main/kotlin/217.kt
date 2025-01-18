package org.mdu


// https://leetcode.com/problems/contains-duplicate/
fun containsDuplicate(nums: IntArray): Boolean = nums.groupBy { it }.filter { it.value.size > 1 }.any()

fun main() {
    println(containsDuplicate(intArrayOf(1, 2, 3, 1)))
    println(containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))
    println(!containsDuplicate(intArrayOf(1, 2, 3, 4)))
}