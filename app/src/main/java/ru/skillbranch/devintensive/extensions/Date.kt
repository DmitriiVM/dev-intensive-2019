package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - time
    if (diff < 0) return "Некорректная дата"
    return when (diff) {
        in 0..SECOND -> "только что"
        in SECOND..45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND..75 * SECOND -> "минуту назад"
        in 75 * SECOND..45 * MINUTE -> " назад"
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in 75 * MINUTE..22 * HOUR -> " назад"
        in 22 * HOUR..26 * HOUR -> "день назад"
        in 26 * HOUR..360 * DAY -> " назад"
        else -> "более года назад"
    }
}

//fun Date.humanizeDiff(date: Date = Date()): String {
//    val diff = date.time - time
//    if (diff < 0) return "Некорректная дата"
//    return when (diff) {
//        in 0..SECOND -> "только что"
//        in SECOND..45 * SECOND -> "несколько секунд назад"
//        in 45 * SECOND..75 * SECOND -> "минуту назад"
//        in 75 * SECOND..45 * MINUTE -> "${TimeUnits.MINUTE.plural((diff / MINUTE).toInt())} назад"
//        in 45 * MINUTE..75 * MINUTE -> "час назад"
//        in 75 * MINUTE..22 * HOUR -> "${TimeUnits.HOUR.plural((diff / HOUR).toInt())} назад"
//        in 22 * HOUR..26 * HOUR -> "день назад"
//        in 26 * HOUR..360 * DAY -> "${TimeUnits.DAY.plural((diff / DAY).toInt())} назад"
//        else -> "более года назад"
//    }
//}

//fun TimeUnits.plural(value: Int): String {
//    val lastTwoNumbers = value.toString().takeLast(2).toInt()
//    val lastNumber = value.toString().last().toString().toInt()
//    val strings = when (this) {
//        TimeUnits.SECOND -> arrayListOf("секунд", "секунду", "секунды")
//        TimeUnits.MINUTE -> arrayListOf("минут", "минуту", "минуты")
//        TimeUnits.HOUR -> arrayListOf("часов", "час", "часа")
//        TimeUnits.DAY -> arrayListOf("дней", "день", "дня")
//    }
//    return when {
//        lastTwoNumbers in 11..14 -> "$value ${strings[0]}"
//        lastNumber == 1 -> "$value ${strings[1]}"
//        lastNumber in 2..4 -> "$value ${strings[2]}"
//        else -> "$value ${strings[0]}"
//    }
//}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}