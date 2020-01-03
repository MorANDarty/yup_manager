package com.yup.manager.domain.utils

import androidx.room.FtsOptions
import com.yup.manager.domain.entities.order.OrderSample
import java.util.ArrayList


//created by Ilmir Shagabiev

fun getSomeOrders():MutableList<OrderSample>{
    val list = ArrayList<OrderSample>()
    list.add(OrderSample("1","13:20", "Виталий Царь", "Столик в ресторане"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked", "13", true))
    list.add(OrderSample("2","13:40", "Виталий Царь", "Прокат коньков"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked",null, false))
    list.add(OrderSample("3","13:45", "Ильмир Шагабиев", "Прокат лыж"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","checked", null, false))
    list.add(OrderSample("4","13:55", "Виталий Царь", "Прокат коньков"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked",null, false))
    list.add(OrderSample("5","18:10", "Булат Каримов", "Номер в отеле"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked", "18", true))
    list.add(OrderSample("6","18:40", "Виталий Царь", "Прокат коньков"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked",null, false))
    list.add(OrderSample("7","20:10", "Эрик Ф.", "Прокат проката"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked", "20", true))
    list.add(OrderSample("8","20:20", "Булат Каримов", "Прокат проката проката"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","checked", null, false))
    return list
}