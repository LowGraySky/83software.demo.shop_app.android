package ru.eightythreesoftware.shop_app.android.demo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.model.Repository
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.GroceryViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.GroceryViewModelFactory
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductsViewModel
    private lateinit var groceryViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productViewModel = ViewModelProvider(this,
            ProductsViewModelFactory(
                Repository(
                    RetrofitService.newInstance()
                )
            )
        )[ProductsViewModel::class.java]
        groceryViewModel = ViewModelProvider(this, GroceryViewModelFactory())[GroceryViewModel::class.java]
        val navigationHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navigationController = navigationHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navigationController)
    }

    override fun onDestroy() {
        super.onDestroy()
        productViewModel.stop()
    }
}