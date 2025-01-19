package org.mdu

fun isAnagramHash(s: String, t: String): Boolean =
    s.length == t.length && IntArray(24).apply {
        s.forEach { this[it - 'a']++ }
        t.forEach { this[it - 'a']-- }
    }.indexOfFirst { it != 0 } == -1

fun isAnagramSort(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    return s.toCharArray().sortedArray().contentEquals(t.toCharArray().sortedArray())
}

fun main() {
    check(isAnagramHash("rat", "tar"))
    check(isAnagramHash("anagram", "nagaram"))
    check(!isAnagramHash("mateusz", "usz"))

    check(isAnagramSort("rat", "tar"))
    check(isAnagramSort("anagram", "nagaram"))
    check(!isAnagramSort("mateusz", "usz"))
}