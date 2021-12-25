package ru.eightythreesoftware.shop_app.android.demo.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.GroceryViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModel

class ProductDetailsFragment: Fragment() {

    private val productsViewModel: ProductsViewModel by activityViewModels()
    private val groceryViewModel: GroceryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.product_details_fragment, container, false)
        productsViewModel.singleProduct.observe(viewLifecycleOwner){ product ->
            setParams(view, product)
        }
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun setParams(view: View, product: Product){
        try {
            val nameView: TextView = view.findViewById(R.id.product_details_product_name)
            val abvView: TextView = view.findViewById(R.id.product_details_product_abv)
            val descriptionView: TextView = view.findViewById(R.id.product_details_product_description)
            val ingredientsView: TextView = view.findViewById(R.id.product_details_product_ingredients)
            val price: TextView = view.findViewById(R.id.product_details_product_price)
            val imageView: ImageView = view.findViewById(R.id.product_details_product_image)
            val addToGroceryButton: ImageView = view.findViewById(R.id.product_details_add_to_grocery_button)
            nameView.text = product.name
            abvView.text = "${product.abv}%"
            descriptionView.text = """
            Дополнительная информация:
            
            ${product.description?.toString() ?: "NO INFO"}
            """.trimIndent()
            ingredientsView.text = """
            Список ингредицентов:
            
            ${product.ingredients?.toString() ?: "NO INFO"}
            """.trimIndent()
            price.text = "${product.price.toString() ?: 0.0}$"
            addToGroceryButton.setOnClickListener {
                addToGrocery(product)
            }
            Glide.with(imageView)
                .load(product.image)
                .error(R.drawable.no_image_error)
                .placeholder(R.drawable.loading_image)
                .into(imageView)
        }catch (throwable: Throwable){
            Toast.makeText(
                this.context,
                "Не удалось открыть детальное описание продукта",
                Toast.LENGTH_LONG
            ).show().also {
                Log.d("MAIN_DEBUG", "FAILED: failed to open product details fragment ERROR: ${throwable.message}")
            }
        }
    }

    private fun addToGrocery(product: Product){
        groceryViewModel.addToGrocery(product)
        Toast.makeText(this.context,
            "${product.name} добавлен в корзину",
            Toast.LENGTH_LONG)
            .show()
    }
}