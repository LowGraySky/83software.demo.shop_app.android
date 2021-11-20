package ru.eightythreesoftware.shop_app.android.demo.view.recycler_views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.network.ProductResponse

class ProductsListRecyclerViewAdapter(private val products: List<ProductResponse>): RecyclerView.Adapter<ProductsListRecyclerViewAdapter.ViewHolder>() {

     inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.product_list_product_icon)
        val abv: TextView = itemView.findViewById(R.id.product_list_product_abv)
        val name: TextView = itemView.findViewById(R.id.product_list_product_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_list_view_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsListRecyclerViewAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.name
        holder.abv.text = product.abv.toString()
        Glide.with(holder.image)
            .load(product.image_url)
            .error(R.drawable.no_image_error)
            .placeholder(R.drawable.loading_image)
            .into(holder.image)
    }

    override fun getItemCount() = products.size
}