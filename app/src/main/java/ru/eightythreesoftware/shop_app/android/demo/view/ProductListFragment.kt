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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.view.recycler_views.ProductsListRecyclerViewAdapter
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModel
import java.lang.IllegalArgumentException

class ProductListFragment : Fragment() {

    private val viewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.product_list_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.product_list_recycler_view)
        viewModel.productsList.observe(viewLifecycleOwner, { products ->
            recyclerView.adapter = ProductsListRecyclerViewAdapter(products){ product: Product ->
                showDetailsFragment(product)
            }
            recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false )
            }
        )
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
            viewModel.selectSingleProduct(product)
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