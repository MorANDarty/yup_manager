package com.yup.manager.domain.entities.user

import com.google.gson.annotations.SerializedName
import com.yup.manager.domain.entities.order.accessory.Profile


data class UserInfoResp(
    @SerializedName("user") var userBlock: UserBlock
)

data class UserBlock(
    @SerializedName("profile") var profile:Profile
)