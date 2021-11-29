package ru.eightythreesoftware.shop_app.android.demo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.view.recycler_views.GroceryProductsListRecyclerViewAdapter
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.GroceryViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModel
import java.lang.IllegalArgumentException

class GroceryFragment : Fragment() {

    private val groceryViewModel: GroceryViewModel by activityViewModels()
    private val productViewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.grocery_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.grocery_recycler_view)
        val productsCounter: TextView = view.findViewById(R.id.grocery_fragment_products_counter)
        val finalPrice: TextView = view.findViewById(R.id.grocery_fragment_final_price)
        groceryViewModel.grocery.observe(viewLifecycleOwner){ products ->
            recyclerView.adapter = GroceryProductsListRecyclerViewAdapter(
                products,
                { product: Product ->  removeFromGrocery(product) },
                { product: Product-> showDetailsFragment(product) }
            )
            productsCounter.text = products?.size.toString()
            finalPrice.text = setFinalPrice(products).toString()
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        return view
    }

    private fun removeFromGrocery(product: Product){
        groceryViewModel.removeFromGrocery(product)
        Toast.makeText(this.context,
            "${product.name} удален из корзины",
            Toast.LENGTH_LONG)
            .show()
    }

    private fun setFinalPrice(products: List<Product>): Double{
        val finalPrice = 0.0
        products.forEach { it.price.plus(finalPrice) }.also {
            Log.d("MAIN_DEBUG", "FINAL PRICE CURRENT VALUE: $finalPrice")
        }
        return finalPrice
    }


    private fun showDetailsFragment(product: Product){
        try {
            productViewModel.selectSingleProduct(product)
            this.findNavController().navigate(R.id.action_grocery_fragment_to_product_details_fragment)
        }catch (exception: IllegalArgumentException){
            Toast.makeText(this.context, "Not found source destination fragment", Toast.LENGTH_LONG)
                .show()
                .also {
                    Log.d("MAIN_DEBUG", "ERROR: NOT FOUND SOURCE DESTINATION FRAGMENT, ${exception.message}")
                }
        }
    }
}