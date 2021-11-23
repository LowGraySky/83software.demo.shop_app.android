package ru.eightythreesoftware.shop_app.android.demo.model

import android.util.Log
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService

class Repository(private val retrofitService: RetrofitService) {

    fun getProductList() =
        retrofitService.service
            .getProductList()
            .subscribeOn(Schedulers.io())
            .doOnError { throwable ->
                throwable.message?.let { Log.d("MAIN_DEBUG", "FAIL: Products list hasn't been downloaded, ERROR: $it") }
            }


    fun getProductById(id: Int) =
        retrofitService.service
            .getProductByID(id)
            .subscribeOn(Schedulers.io())
            .doOnError { throwable ->
                throwable.message?.let { Log.d( "MAIN_DEBUG", "FAIL: Product with ID($id) hasn't been downloaded, ERROR: $it") }
            }
}
