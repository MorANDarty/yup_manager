package com.yup.manager.domain.utils

import androidx.room.FtsOptions
import com.yup.manager.domain.entities.order.OrderSample
import java.util.ArrayList


//created by Ilmir Shagabiev

fun getSomeOrders():MutableList<OrderSample>{
    val list = ArrayList<OrderSample>()
    list.add(OrderSample("1","13:20", "Виталий Царь", "Прокат жопы"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked"))
    list.add(OrderSample("2","13:40", "Виталий Царь", "Прокат жопы"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked"))
    list.add(OrderSample("3","13:20", "Ильмир Шагабиев", "Прокат жопы"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","checked"))
    list.add(OrderSample("4","13:20", "Вадим Горбунов", "Прокат жопы"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked"))
    list.add(OrderSample("5","13:20", "Эрик Ф.", ""
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","unchecked"))
    list.add(OrderSample("6","13:20", "Булат Каримов", "Прокат жопы"
        ,"https://i.ytimg.com/vi/7KLTBuqFcYQ/hqdefault.jpg","checked"))
    return list
}