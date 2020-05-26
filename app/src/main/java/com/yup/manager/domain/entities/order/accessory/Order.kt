package com.yup.manager.domain.entities.order.accessory

import com.google.gson.annotations.SerializedName


data class Order(
    @SerializedName("_id") var id: String,
    @SerializedName("service_name") var name: String,
    @SerializedName("time") var time: Time,
    @SerializedName("cost") var cost: String,
    @SerializedName("comment") var comment: String,
    @SerializedName("maxParticipants") var maxParticipants: Int,
    @SerializedName("service_img") var img: String,
    @SerializedName("service_description") var description: String,
    @SerializedName("service_location") var location: Location,
    @SerializedName("status") var state: List<String>,
    @SerializedName("company_id") var companyId: String,
    @SerializedName("service_address") var serviceAddress: String,
    @SerializedName("onPlaceWay") var onPlaceWay: String,
    @SerializedName("owner") var owner: Owner
)

data class UpdateOrderResp(
    @SerializedName("order") var order: RespUpdateOrder
)

data class RespUpdateOrder(
    @SerializedName("status") var status:List<String>
)
