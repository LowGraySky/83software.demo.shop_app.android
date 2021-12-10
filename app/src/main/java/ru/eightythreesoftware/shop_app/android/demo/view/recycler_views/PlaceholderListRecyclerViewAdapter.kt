package ru.eightythreesoftware.shop_app.android.demo.view.recycler_views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.network.Placeholder

class PlaceholderListRecyclerViewAdapter(private val placeholders: List<Placeholder>):
    RecyclerView.Adapter<PlaceholderListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.products_list_list_recycler_view_product_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.products_list_list_view_holder,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val placeholder = placeholders[position]
        Glide.with(holder.image)
            .load(placeholder.image_jpg)
            .error(R.drawable.no_image_error)
            .placeholder(R.drawable.loading_image)
            .into(holder.image)
    }

    override fun getItemCount(): Int = placeholders.size
}