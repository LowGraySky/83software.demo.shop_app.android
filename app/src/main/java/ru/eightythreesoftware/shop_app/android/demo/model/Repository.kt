package ru.eightythreesoftware.shop_app.android.demo.model

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService

class Repository(private val retrofitService: RetrofitService) {

    fun getProductList() =
        retrofitService.service
            .getProductList()
            .observeOn(Schedulers.io())
            .subscribe()

    fun getProductById(id: Int) =
        retrofitService.service
            .getProductByID(id)
            .observeOn(Schedulers.io())
            .subscribe()
}