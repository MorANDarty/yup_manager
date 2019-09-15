package com.yup.manager.app.ui.main.orders

import androidx.recyclerview.widget.DiffUtil
import com.yup.manager.domain.entities.order.OrderSample

//created by Ilmir Shagabiev

class OrdersDiffUtils(private val oldList:List<OrderSample>,
                      private val newList:List<OrderSample>) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition].customerName == newList[newItemPosition].customerName
               && oldList[oldItemPosition].name == newList[newItemPosition].name
               && oldList[oldItemPosition].time == newList[newItemPosition].time
    }


}