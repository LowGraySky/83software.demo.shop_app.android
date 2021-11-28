package ru.eightythreesoftware.shop_app.android.demo.model

import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.ProductResponse

class Repository(private val retrofitService: RetrofitService) {

    fun getProductList(): Single<List<Product>> =
        retrofitService.service
            .getProductList()
            .map{ Mapper.toProductList(it)}
            .subscribeOn(Schedulers.io())
            .doOnError { throwable ->
                throwable.message?.let { Log.d("MAIN_DEBUG", "FAIL: Products list hasn't been downloaded, ERROR: $it") }
            }


    fun getProductById(id: Int): Single<Product> =
        retrofitService.service
            .getProductByID(id)
            .subscribeOn(Schedulers.io())
            .map { Mapper.toProduct(it) }
            .doOnError { throwable ->
                throwable.message?.let { Log.d( "MAIN_DEBUG", "FAIL: Product with ID($id) hasn't been downloaded, ERROR: $it") }
            }
}
