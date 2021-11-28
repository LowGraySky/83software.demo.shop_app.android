package ru.eightythreesoftware.shop_app.android.demo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.ProductResponse
import ru.eightythreesoftware.shop_app.android.demo.view.recycler_views.ProductsListRecyclerViewAdapter
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.GroceryViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModel

class GroceryFragment : Fragment() {

    private val groceryViewModel: GroceryViewModel by activityViewModels()
    private val productViewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.grocery_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.grocery_recycler_view)
        groceryViewModel.grocery.observe(viewLifecycleOwner){ products ->
            recyclerView.adapter = ProductsListRecyclerViewAdapter(products){

            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        return view
    }
}