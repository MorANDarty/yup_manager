package com.yup.manager.domain.entities.order.accessory

import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("lng") var longitude: Double,
    @SerializedName("lat") var latitude: Double
)