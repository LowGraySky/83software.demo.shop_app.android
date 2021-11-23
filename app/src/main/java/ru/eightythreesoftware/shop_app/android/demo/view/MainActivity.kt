package ru.eightythreesoftware.shop_app.android.demo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Repository
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModel
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.ProductsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this,
            ProductsViewModelFactory(
                Repository(
                    RetrofitService.newInstance()
                )
            )
        ).get(ProductsViewModel::class.java)
        val navigationHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navigationController = navigationHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navigationController)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stop()
    }
}