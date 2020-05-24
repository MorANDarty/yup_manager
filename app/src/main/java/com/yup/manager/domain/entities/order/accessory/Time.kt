package com.yup.manager.domain.entities.order.accessory

import com.google.gson.annotations.SerializedName


data class Time(
    @SerializedName("from") var from: String,
    @SerializedName("to") var to: String
)