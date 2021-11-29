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

class GroceryProductsListRecyclerViewAdapter(
    private val products: List<Product>,
    private val removeFromGroceryClickListener: (Product) -> Unit,
    private val openDetailsFragmentClickListener: (Product) -> Unit
    ):
    RecyclerView.Adapter<GroceryProductsListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.grocery_fragment_product_image)
        val name: TextView = itemView.findViewById(R.id.grocery_fragment_product_name)
        val price: TextView = itemView.findViewById(R.id.grocery_fragment_product_price)
        val removeButton: ImageView = itemView.findViewById(R.id.grocery_fragment_remove_from_grocery_button)

        fun binListener(
            product: Product,
            removeListener: (Product) -> Unit,
            detailsListener: (Product) -> Unit
        ){
            removeButton.setOnClickListener{
                removeListener(product)
            }
            image.setOnClickListener {
                detailsListener(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.grocery_product_row_view_holder, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.name
        holder.price.text = "${product.price.toString()}$"
        Glide
            .with(holder.image)
            .load(product.image)
            .error(R.drawable.no_image_error)
            .placeholder(R.drawable.loading_image)
            .into(holder.image)
        holder.binListener(
            product,
            removeFromGroceryClickListener,
            openDetailsFragmentClickListener
        )
    }

    override fun getItemCount(): Int = products.size
}