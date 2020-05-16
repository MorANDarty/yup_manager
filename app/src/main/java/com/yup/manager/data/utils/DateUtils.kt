package com.yup.manager.data.utils

import java.text.SimpleDateFormat
import java.util.*


fun getCurrentYear(): Int {
    val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
    calendar.time = Date()
    return calendar.get(Calendar.YEAR)
}

fun getCurrentMonth(): Int {
    val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
    calendar.time = Date()
    return calendar.get(Calendar.MONTH)
}

fun getCurrentDay(): Int {
    val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
    calendar.time = Date()
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun getDayOfWeekString(day:Int):String{
    when(day){
        0-> return "Пнд"
        1-> return "Вт"
        2-> return "Ср"
        3-> return "Чт"
        4-> return "Пт"
        5-> return "Сб"
        6-> return "Вс"
    }
    return ""
}

fun getStringDate(year: Int, month: Int, day: Int): String {
    when (month) {
        0 -> return "$day января"
        1 -> return "$day февраля"
        2 -> return "$day марта"
        3 -> return "$day апреля"
        4 -> return "$day мая"
        5 -> return "$day июня"
        6 -> return "$day июля"
        7 -> return "$day августа"
        8 -> return "$day сентября"
        9 -> return "$day октября"
        10 -> return "$day ноября"
        11 -> return "$day декабря"
    }
    return ""
}

fun getDateAndTimeString(dateString: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date = format.parse(dateString)
    val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
    val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
    val monthFormat = SimpleDateFormat("MM", Locale.getDefault())
    val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())

    val timeValue = timeFormat.format(date)
    val day = dayFormat.format(date)
    val month = monthFormat.format(date)
    val year = yearFormat.format(date)
    return "${getStringDate(year.toInt(), month.toInt() - 1, day.toInt())}, $timeValue"
}