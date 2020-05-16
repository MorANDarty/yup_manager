package com.yup.manager.app.ui.main.orders

import com.yup.manager.domain.entities.order.accessory.Order


interface OrdersCallback {

    fun onCallback(order:Order)

}