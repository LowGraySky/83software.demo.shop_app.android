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
import ru.eightythreesoftware.shop_app.android.demo.model.Product

class SearchRecyclerViewAdapter(private val products: List<Product>): RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val productImage: ImageView = itemView.findViewById(R.id.search_activity_view_holder_product_image)
        val productName: TextView = itemView.findViewById(R.id.search_activity_view_holder_product_name)
        val productCategory: TextView = itemView.findViewById(R.id.search_activity_view_holder_product_category)
        val productPrice: TextView = itemView.findViewById(R.id.search_activity_view_holder_product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.search_view_product_raw_view_holder,
                    parent,
                    false
                )
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val product = products[position]
        holder.productPrice.text = "${product.price}$"
        holder.productCategory.text = "Product"
        holder.productName.text = product.name
        Glide
            .with(holder.productImage)
            .load(product.image)
            .error(R.drawable.no_image_error)
            .placeholder(R.drawable.loading_image)
            .into(holder.productImage)
    }

    override fun getItemCount(): Int =
        products.size
}