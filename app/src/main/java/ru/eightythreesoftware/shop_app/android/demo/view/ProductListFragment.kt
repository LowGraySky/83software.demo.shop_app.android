package ru.eightythreesoftware.shop_app.android.demo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductListViewModel

class ProductListFragment : Fragment() {

    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.product_list_fragment, container, false)
        view.findViewById<Button>(R.id.button1_next).setOnClickListener {
            this.findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment2)
        }
        return view
    }

    companion object {
        fun newInstance() = ProductListFragment()
    }
}