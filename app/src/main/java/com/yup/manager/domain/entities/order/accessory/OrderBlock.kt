package com.yup.manager.domain.entities.order.accessory

import com.google.gson.annotations.SerializedName


data class OrderBlock(
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: String,
    @SerializedName("orders") var orders: List<Order>,
    @SerializedName("cost") var cost: Int
)