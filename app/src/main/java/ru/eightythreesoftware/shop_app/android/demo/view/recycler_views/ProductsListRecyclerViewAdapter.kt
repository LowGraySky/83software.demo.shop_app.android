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

class ProductsListRecyclerViewAdapter(
    private val products: List<Product>,
    private val clickListener: (Product) -> Unit
    ): RecyclerView.Adapter<ProductsListRecyclerViewAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.product_list_product_icon)
        val price: TextView = itemView.findViewById(R.id.product_list_product_price)
        val abv: TextView = itemView.findViewById(R.id.product_list_product_abv)

        fun binListener(product: Product, listener: (Product) -> Unit){
            itemView.setOnClickListener{
                 listener(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_list_row_view_holder, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsListRecyclerViewAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.abv.text = "${product.abv}%"
        holder.price.text = "${product.price}$"
        Glide.with(holder.image)
            .load(product.image)
            .error(R.drawable.no_image_error)
            .placeholder(R.drawable.loading_image)
            .into(holder.image)
        holder.binListener(product, clickListener)
    }


    override fun getItemCount() = products.size
}