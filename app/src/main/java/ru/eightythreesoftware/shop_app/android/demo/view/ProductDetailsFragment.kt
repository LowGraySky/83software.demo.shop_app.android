package ru.eightythreesoftware.shop_app.android.demo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductDetailsViewModel

class ProductDetailsFragment : Fragment() {

    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.product_details_fragment, container, false)
        view.findViewById<Button>(R.id.button2_prev).setOnClickListener {
            this.findNavController().navigate(R.id.action_productDetailsFragment_to_productListFragment)
        }
        return view
    }

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }
}