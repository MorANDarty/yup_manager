package com.yup.manager.domain.entities.order

import com.google.gson.annotations.SerializedName
import com.yup.manager.domain.entities.order.accessory.Order
import com.yup.manager.domain.entities.order.accessory.OrderBlock


data class RespOrdersList(

    @SerializedName("info") var blocks: List<OrderBlock>

)
