package ru.eightythreesoftware.shop_app.android.demo.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.ProductResponse

interface NetworkService {
    @GET("/v2/beers/")
    fun getProductList(): Single<List<ProductResponse>>

    @GET("/v2/beers/{id}")
    fun getProductByID(@Path("id") id: Int): Single<ProductResponse>
}