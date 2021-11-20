package ru.eightythreesoftware.shop_app.android.demo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.network.ProductResponse

class ProductDetailsFragment(private val product: ProductResponse) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.product_details_fragment, container, false)
    }

    private fun setParams(view: View, product: ProductResponse){
        val nameView: TextView = view.findViewById(R.id.product_details_product_name)
        val abvView: TextView = view.findViewById(R.id.product_details_product_abv)
        val descriptionView: TextView = view.findViewById(R.id.product_details_product_description)
        val ingredientsView: TextView = view.findViewById(R.id.product_details_product_ingredients)
        val imageView: ImageView = view.findViewById(R.id.product_details_product_image)
        nameView.text = product.name
        abvView.text = product.abv.toString()
        descriptionView.text = product.description
        ingredientsView.text = product.ingredients
        Glide.with(imageView)
            .load(product.image_url)
            .error(R.drawable.no_image_error)
            .placeholder(R.drawable.loading_image)
            .into(imageView)
    }
}