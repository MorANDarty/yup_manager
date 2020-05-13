package com.yup.manager.domain.entities.order

import com.google.gson.annotations.SerializedName
import com.yup.manager.domain.entities.order.accessory.Order


data class RespOrdersList(
    @SerializedName("orders") var orders:List<Order>
)