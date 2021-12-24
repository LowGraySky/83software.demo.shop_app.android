package ru.eightythreesoftware.shop_app.android.demo.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private val PRODUCTS_BASE_URL: String = "https://api.punkapi.com/"
    private val USERS_BASE_URL: String = "https://random-data-api.com/api/"
    private val RESTAURANT_PHOTOS_BASE_URL: String = "https://picsum.photos/"

    private val productRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(PRODUCTS_BASE_URL)
        .build()

    private val usersRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(USERS_BASE_URL)
        .build()

    private val restaurantsRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(RESTAURANT_PHOTOS_BASE_URL)
        .build()

    val restaurantsPhotosService: RestaurantsPhotosService = restaurantsRetrofit.create(RestaurantsPhotosService::class.java)
    val productsService: ProductService = productRetrofit.create(ProductService::class.java)
    val userService: UserService = usersRetrofit.create(UserService::class.java)

    companion object{
        fun newInstance() = RetrofitService()
    }
}