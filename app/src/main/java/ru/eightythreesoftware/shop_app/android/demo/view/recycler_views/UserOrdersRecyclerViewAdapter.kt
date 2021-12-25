package ru.eightythreesoftware.shop_app.android.demo.view.recycler_views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Order
import ru.eightythreesoftware.shop_app.android.demo.model.Product

class UserOrdersRecyclerViewAdapter(private val orders: List<Order>):
    RecyclerView.Adapter<UserOrdersRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val price: TextView = itemView.findViewById(R.id.order_fragment_order_price)
        val dateTime: TextView = itemView.findViewById(R.id.order_fragment_order_date_time)
        val address: TextView = itemView.findViewById(R.id.order_fragment_order_address)
        val products: TextView = itemView.findViewById(R.id.order_fragment_order_products_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_list_row_view_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.address.text = order.orderAddress
        holder.dateTime.text = order.dateTime.toString()
        holder.price.text = order.orderPrice.toString()
        holder.products.text = order.productsInOrder.toString()
    }

    override fun getItemCount(): Int = orders.size
}