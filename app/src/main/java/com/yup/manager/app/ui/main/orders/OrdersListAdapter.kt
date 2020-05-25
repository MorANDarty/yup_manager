package com.yup.manager.app.ui.main.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yup.manager.R
import com.yup.manager.data.utils.getDateAndTimeString
import com.yup.manager.domain.entities.order.accessory.Order
import com.yup.manager.domain.utils.*
import kotlinx.android.synthetic.main.item_order.view.*


//created by Ilmir Shagabiev

class OrdersListAdapter(
    private var data: MutableList<Order>,
    private val callback: OrdersCallback
) :
    RecyclerView.Adapter<OrdersListAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_order,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateData(newList: List<Order>?) {
        val diffCallback = newList?.let { OrdersDiffUtils(data, it) }
        val diffResults = diffCallback?.let { DiffUtil.calculateDiff(it) }

        data = newList as MutableList<Order>
        diffResults?.dispatchUpdatesTo(this)
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Order) {
            itemView.tv_customer_name.text = order.owner.name + " "+order.owner.surname
            itemView.tv_order_name_item.text = order.name
            itemView.tv_time_item.text = getDateAndTimeString(order.time.from)
            Glide.with(itemView).load(order.owner.picture).into(itemView.img_avatar_item)

            itemView.setOnClickListener {
                callback.onCallback(order)
            }

            when (order.state.first()) {
                STATE_REJECTED, STATE_TIMEOUT -> {
                    itemView.ll_info.background =
                        itemView.resources.getDrawable(R.drawable.outline_order_rejected)
                    itemView.ll_info.clipToOutline = true
                    itemView.tv_customer_name.setTextColor(itemView.resources.getColor(R.color.white))
                    itemView.tv_order_name_item.setTextColor(itemView.resources.getColor(R.color.white))
                }

                STATE_EXPECTATION -> {
                    itemView.ll_info.background =
                        itemView.resources.getDrawable(R.drawable.outline_order_expectation)
                    itemView.ll_info.clipToOutline = true
                }

                STATE_APPROVED -> {
                    itemView.ll_info.background =
                        itemView.resources.getDrawable(R.drawable.outline_order_approved)
                    itemView.ll_info.clipToOutline = true
                }

                STATE_COMPLETED -> {
                    itemView.ll_info.background =
                        itemView.resources.getDrawable(R.drawable.outline_order_completed)
                    itemView.ll_info.clipToOutline = true
                    itemView.tv_customer_name.setTextColor(itemView.resources.getColor(R.color.white))
                    itemView.tv_order_name_item.setTextColor(itemView.resources.getColor(R.color.white))
                }
            }
        }
    }
}