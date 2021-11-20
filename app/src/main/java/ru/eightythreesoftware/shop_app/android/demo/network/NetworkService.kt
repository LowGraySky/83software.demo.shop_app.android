package ru.eightythreesoftware.shop_app.android.demo.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET
    fun getProductList(): Single<List<ProductResponse>>

    @GET("{id}")
    fun getProductByID(@Path("id") id: Int): Single<ProductResponse>
}