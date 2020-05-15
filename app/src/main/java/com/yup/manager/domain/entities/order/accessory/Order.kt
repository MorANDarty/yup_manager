package com.yup.manager.domain.entities.order.accessory

import com.google.gson.annotations.SerializedName


data class Order(
    @SerializedName("_id") var id: String,
    @SerializedName("service_name") var name: String,
    @SerializedName("time") var time: String,
    @SerializedName("cost") var cost: String,
    @SerializedName("comment") var comment: String,
    @SerializedName("participants") var maxParticipants: Int,
    @SerializedName("service_img") var img: String,
    @SerializedName("service_description") var description: String,
    @SerializedName("service_location") var location: Location,
    @SerializedName("state") var state: String,
    @SerializedName("company_id") var companyId: String,
    @SerializedName("service_address") var serviceAddress: String,
    @SerializedName("onPlaceWay") var onPlaceWay: String,
    @SerializedName("user_name") var userName: String,
    @SerializedName("user_surname") var userSurname: String,
    @SerializedName("user_phone") var userPhone: String,
    @SerializedName("user_avatar") var userAvatar:String
)
