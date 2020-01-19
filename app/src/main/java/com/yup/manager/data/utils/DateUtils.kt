package com.yup.manager.data.utils

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