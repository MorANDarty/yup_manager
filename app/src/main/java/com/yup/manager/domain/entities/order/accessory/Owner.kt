package com.yup.manager.domain.entities.order.accessory

import com.google.gson.annotations.SerializedName


data class Owner(
    @SerializedName("name") var name: String,
    @SerializedName("surname") var surname: String,
    @SerializedName("picture") var picture: String,
    @SerializedName("phone") var phone: String
)