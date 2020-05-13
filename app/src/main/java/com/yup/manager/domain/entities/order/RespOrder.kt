package com.yup.manager.domain.entities.order

import com.google.gson.annotations.SerializedName
import com.yup.manager.domain.entities.order.accessory.Order


data class RespOrder(
    @SerializedName("order") var order: Order
)