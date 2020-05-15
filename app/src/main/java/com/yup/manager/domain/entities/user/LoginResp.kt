package com.yup.manager.domain.entities.user

import com.google.gson.annotations.SerializedName


data class LoginResp(@SerializedName("token") val token: String)
