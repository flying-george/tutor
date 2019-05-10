package ru.corney.george.tutor

class Converter {

    /**
     * Задача этой функции - перевести строку, содержащую двоичный код числа
     * в строку, содержащую десятичный код числа
     */
    fun binToDec(bin: String): String {
        var acc = 0
        for (char in bin.chars()) {
            val b = char - '0'.toInt()
            acc = acc * 2 + b
        }

        return "" + acc
    }

    /**
     * Задача этой функции - перевести строку, содержащую десятичный код числа
     * в строку, содержащую двоичный код числа
     */
    fun decToBin(dec: String): String {
        return ""
    }
}

fun toLong(str: String): Long {
    var result: Long = 0L

    for (c in str) {
        if (c<'0') {
            throw IllegalArgumentException("Not a number")
        }

        if (c>'9') {
            throw IllegalArgumentException("Not a number")
        }
            val i = c - '0'
            result = result * 10 + i

    }
    return result
}

fun main() {
    val converter = Converter()

    val f = toLong("1024")
    println(f)


    val dec = converter.binToDec("10001")

    val bin = converter.decToBin("1023")

    println("Dec: " + dec)
    println("Bin: " + bin)
}