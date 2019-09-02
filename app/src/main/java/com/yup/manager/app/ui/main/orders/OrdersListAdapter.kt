package com.yup.manager.app.ui.main.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yup.manager.R
import com.yup.manager.domain.entities.order.OrderSample
import kotlinx.android.synthetic.main.item_order.view.*

//created by Ilmir Shagabiev

class OrdersListAdapter(private var data: MutableList<OrderSample>) :
    RecyclerView.Adapter<OrdersListAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addItem(order: OrderSample, position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
        data.add(order)
        notifyItemInserted(data.size)
    }

    fun removeAt(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: OrderSample) {
            if (order.state != "canceled") {
                itemView.tv_customer_name.text = order.customerName
                itemView.tv_order_name_item.text = order.name
                itemView.tv_time_item.text = order.time
                Glide.with(itemView).load(order.avatar).into(itemView.img_avatar_item)
                if (order.state == "unchecked") {
                    itemView.background = itemView.resources.getDrawable(R.drawable.outline_order_unchecked)
                    itemView.clipToOutline = true
                    itemView.tag = "unchecked"
                }
            }
        }
    }
}