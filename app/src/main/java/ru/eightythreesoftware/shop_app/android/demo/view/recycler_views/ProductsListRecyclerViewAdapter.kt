package ru.eightythreesoftware.shop_app.android.demo.view.recycler_views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.ProductResponse

class ProductsListRecyclerViewAdapter(private val products: List<ProductResponse>): RecyclerView.Adapter<ProductsListRecyclerViewAdapter.ViewHolder>() {

     inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.product_list_product_icon)
        val abv: TextView = itemView.findViewById(R.id.product_list_product_abv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_list_view_holder, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsListRecyclerViewAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.abv.text = "${product.abv}%"
        Glide.with(holder.image)
            .load(product.image_url)
            .error(R.drawable.no_image_error)
            .placeholder(R.drawable.loading_image)
            .into(holder.image)
    }

    override fun getItemCount() = products.size
}