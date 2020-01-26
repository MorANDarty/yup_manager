package com.yup.manager.domain.entities.user

import com.google.gson.annotations.SerializedName


data class LoginReq(
    @SerializedName("login") var login: String,
    @SerializedName("password") var password: String
)
