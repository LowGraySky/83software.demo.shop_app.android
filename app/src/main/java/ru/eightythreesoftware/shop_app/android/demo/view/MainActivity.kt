package ru.eightythreesoftware.shop_app.android.demo.view

import android.content.Context
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.eightythreesoftware.shop_app.android.demo.R
import ru.eightythreesoftware.shop_app.android.demo.model.Repository
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService
import ru.eightythreesoftware.shop_app.android.demo.viewmodel.*

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductsViewModel
    private lateinit var groceryViewModel: GroceryViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var ordersViewModel: OrdersViewModel
    private lateinit var locationManager: LocationManager
    private lateinit var restaurantsViewModel: RestaurantsViewModel
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            setupComponents(Repository(
                RetrofitService.newInstance()
                )
            )
        }catch (throwable: Throwable){
            Toast.makeText(
                this,
                " Произошла системная ошибка, пожалуйста перезагрузите приложение",
                Toast.LENGTH_LONG
            ).show()
                .also {
                    Log.d("MAIN_DEBUG", "ERROR: ${throwable.message}")
                }
        }
    }

    private fun setupComponents(repository: Repository){
        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val navigationHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navigationController = navigationHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navigationController)
        createViewModels(repository)
    }

    private fun createViewModels(repository: Repository){
        productViewModel = ViewModelProvider(
            this, ProductsViewModelFactory(repository)
            )[ProductsViewModel::class.java]
        groceryViewModel = ViewModelProvider(
            this, GroceryViewModelFactory()
            )[GroceryViewModel::class.java]
        userViewModel = ViewModelProvider(
            this, UserViewModelFactory(repository)
            )[UserViewModel::class.java]
        ordersViewModel = ViewModelProvider(
            this, OrdersViewModelFactory(repository)
            )[OrdersViewModel::class.java]
        restaurantsViewModel = ViewModelProvider(
            this, RestaurantsViewModelFactory(repository)
        )[RestaurantsViewModel::class.java]
        searchViewModel = ViewModelProvider(
            this, SearchViewModelFactory(repository)
        )[SearchViewModel::class.java]
    }

    override fun onSearchRequested(): Boolean {
        val searchRequestData = Bundle().apply { putBoolean("SEARCH", true) }
        startSearch(
            null,
            false,
            searchRequestData,
            false
        )
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        productViewModel.stop()
    }
}