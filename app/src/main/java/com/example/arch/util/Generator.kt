package com.example.arch.util

import java.util.*

class Generator {
    private val random: Random = Random()

    fun string(length: Int): String {
        val leftLimit = 97 // letter 'a'
        val rightLimit = 122 // letter 'z'
        val buffer = StringBuilder(length)
        for (i in 0 until length) {
            val randomLimitedInt =
                leftLimit + (random.nextFloat() * (rightLimit - leftLimit + 1)).toInt()
            buffer.append(randomLimitedInt.toChar())
        }
        return buffer.toString()
    }

    fun timeoutMillis(limit: Int) = (random.nextInt(limit) * 1000 + 300).toLong()

    fun nextInt(limit: Int): Int {
        return random.nextInt(limit)
    }
}
