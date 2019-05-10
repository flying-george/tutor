package ru.corney.george.tutor

import java.lang.IllegalArgumentException


/**
 *
 */
class StringInt(private val value: String) {
    lateinit var negative: Boolean
    lateinit var digits: Array<Int>

    init {
        var i = 0
        if (value[0] == '-') {
            negative = true
            digits = Array(value.length - 1) {
                    i -> charToDigit(value[i + 1])
            }
        } else {
            negative = false
            digits = Array(value.length) {
                    i -> charToDigit(value[i])
            }
        }
    }


    operator fun plus(other: StringInt): StringInt {
        // Здесь мы накапливаем результат
        var accumulator = ""

        // i - индекс по числу "слева" от знака плюс, this.value
        var i = this.digits.size - 1
        // j - индекс по числу "справа" от знака плюс, other.value
        var j = other.digits.size - 1

        // TODO переделать перенос так, чтобы он был булевым флагом
        // Перед началом работы перенос равен нулю, у нас еще нет прошлого цикла
        var carry = 0
        // нам не важно, какое число длиннее, мы просто ждем, когда будут обработаны и левое, и правое число
        while (i >= 0 || j >=0) {

            // Добавляем значение переноса с прошлого цикла
            var nextDigit = carry

            // Если левое число еще не обработано полностью, берем его очередную цифру
            if (i >= 0) {
                nextDigit += this.digits[i]
                // Сдвигаем указатель ближе к началу строки (к следующему по старшинству разряду)
                i--
            }

            // Если правое число еще не обработано полностью, берем его очередную цифру
            if (j >=0) {
                nextDigit += other.digits[j]
                // Сдвигаем указатель ближе к началу строки (к следующему по старшинству разряду)
                j--
            }

            // Если у нас получилось значение больше 10, вычитаем 10 и устанавливаем значение переноса в 1
            if (nextDigit >= 10) {
                carry = 1
                nextDigit -= 10
            } else {
                carry = 0
            }

            // Добавляем получившуюся цифру перед накопленным значением
            accumulator = digitToChar(nextDigit) + accumulator
        }

        // Если у нас остался необработанный перенос, добавляем его значение
        if (carry != 0) {
            accumulator = digitToChar(carry) + accumulator
        }

        return StringInt(accumulator)
    }

    operator fun minus(other: StringInt): StringInt {
        // Здесь мы накапливаем результат
        var accumulator = ""

        // i - индекс по числу "слева" от знака плюс, this.value
        var i = this.digits.size - 1
        // j - индекс по числу "справа" от знака плюс, other.value
        var j = other.digits.size - 1

        // TODO переделать перенос так, чтобы он был булевым флагом
        // Перед началом работы перенос равен нулю, у нас еще нет прошлого цикла
        var carry = 0
        // нам не важно, какое число длиннее, мы просто ждем, когда будут обработаны и левое, и правое число
        while (i >= 0 || j >=0) {

            // Добавляем значение переноса с прошлого цикла
            var nextDigit = carry

            // Если левое число еще не обработано полностью, берем его очередную цифру
            if (i >= 0) {
                nextDigit += this.digits[i]
                // Сдвигаем указатель ближе к началу строки (к следующему по старшинству разряду)
                i--
            }

            // Если правое число еще не обработано полностью, берем его очередную цифру
            if (j >=0) {
                nextDigit -= this.digits[j]
                // Сдвигаем указатель ближе к началу строки (к следующему по старшинству разряду)
                j--
            }

            // Если у нас получилось значение больше 10, вычитаем 10 и устанавливаем значение переноса в 1
            if (nextDigit < 0) {
                carry = -1
                nextDigit += 10
            } else {
                carry = 0
            }

            // Добавляем получившуюся цифру перед накопленным значением
            accumulator = digitToChar(nextDigit) + accumulator
        }

        // Если у нас остался необработанный перенос, добавляем его значение
        if (carry != 0) {
            accumulator = digitToChar(carry) + accumulator
        }

        return StringInt(accumulator)


    }

    operator fun times(i: StringInt): StringInt {
        return StringInt("")
    }

    operator fun div(i: StringInt): StringInt {
        return StringInt("")
    }
    operator fun rem(i: StringInt): StringInt {
        return StringInt("")
    }

    private fun charToDigit(c: Char): Int {
        val result = c - '0'
        if (result < 0 || result > 9) {
            throw IllegalArgumentException("Not a digit: %c".format(c))
        }
        return result
    }

    private fun digitToChar(digit: Int): Char {
        if (digit < 0 || digit > 9) {
            throw IllegalArgumentException("Not a digit: %d".format(digit))
        }

        return '0' + digit
    }


    /**
     * Специальная функция, возвращающая строковое представление данного объекта
     */
    override fun toString(): String {
        return "SI(%s)".format(value)
    }
}


fun main() {

    val a = StringInt("50")
    val b = StringInt("675")
    val c = a + b

    // Должен вернуть значение 725
    println(c)

    val d = b + c
    // Должен вернуть значение 1400
    println(d)
    val e = StringInt("90")
    val f = StringInt("675")
    val g = e - f
    println(g)

}