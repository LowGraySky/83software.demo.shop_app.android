package ru.eightythreesoftware.shop_app.android.demo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.view.recycler_views.RestaurantsListRecyclerViewAdapter
import ru.eightythreesoftware.shop_app.android.demo.view.recycler_views.ProductsListRecyclerViewAdapter
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.RestaurantsViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.SearchViewModel
import java.lang.IllegalArgumentException

class ProductListFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by activityViewModels()
    private val restaurantsViewModel: RestaurantsViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.product_list_fragment, container, false)
        val productsRowRecyclerView: RecyclerView = view.findViewById(R.id.product_list_row_recycler_view)
        val restaurantsListRecyclerView: RecyclerView = view.findViewById(R.id.profuct_list_restaurants_list_recycler_view)
        productsViewModel.productsList.observe(viewLifecycleOwner, { products ->
            productsRowRecyclerView.adapter = ProductsListRecyclerViewAdapter(products){ product: Product ->
                showDetailsFragment(product)
            }
            productsRowRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false )
            }
        )
        restaurantsViewModel.restaurant.observe(viewLifecycleOwner){ restaurants ->
            restaurantsListRecyclerView.adapter = RestaurantsListRecyclerViewAdapter(restaurants)
            restaurantsListRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchView = view.findViewById<SearchView>(R.id.search_view)
            .setOnSearchClickListener {

            }
    }

    private fun showDetailsFragment(product: Product){
        try {
            productsViewModel.selectSingleProduct(product)
            this.findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment)
        }catch (exception: IllegalArgumentException){
            Toast.makeText(this.context, "Not found source destination fragment", Toast.LENGTH_LONG)
                .show()
                .also {
                    Log.d("MAIN_DEBUG", "ERROR: NOT FOUND SOURCE DESTINATION FRAGMENT, ${exception.message}")
                }
        }
    }


}