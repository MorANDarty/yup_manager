package com.yup.manager.domain.utils

import androidx.room.FtsOptions
import com.yup.manager.domain.entities.order.OrderSample
import java.util.ArrayList


//created by Ilmir Shagabiev

fun getSomeOrders():List<OrderSample>{
    val list = ArrayList<OrderSample>()
    list.add(OrderSample("13:20", "Виталий Царь", "Прокат жопы"
        ,"https://vk.com/your_loover?z=photo143937286_457257335%2Falbum143937286_0%2Frev","unchecked"))
    list.add(OrderSample("13:40", "Виталий Царь", "Прокат жопы"
        ,"https://vk.com/your_loover?z=photo143937286_457257335%2Falbum143937286_0%2Frev","unchecked"))
    list.add(OrderSample("13:20", "Ильмир Шагабиев", "Прокат жопы"
        ,"https://vk.com/your_loover?z=photo143937286_457257335%2Falbum143937286_0%2Frev","checked"))
    list.add(OrderSample("13:20", "Вадим Горбунов", "Прокат жопы"
        ,"https://vk.com/your_loover?z=photo143937286_457257335%2Falbum143937286_0%2Frev","unchecked"))
    list.add(OrderSample("13:20", "Эрик Ф.", ""
        ,"https://vk.com/your_loover?z=photo143937286_457257335%2Falbum143937286_0%2Frev","unchecked"))
    list.add(OrderSample("13:20", "Булат Каримов", "Прокат жопы"
        ,"https://vk.com/your_loover?z=photo143937286_457257335%2Falbum143937286_0%2Frev","checked"))
    return list
}