package com.yup.manager.domain.entities.order

import com.google.gson.annotations.SerializedName


data class ReqUpdateBody(
    @SerializedName("id") var id: String,
    @SerializedName("status") var status: String
)