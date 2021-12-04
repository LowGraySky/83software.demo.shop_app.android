package ru.eightythreesoftware.shop_app.android.demo.model

import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService
import java.time.LocalTime

class Repository(private val retrofitService: RetrofitService) {

    private fun generateRandomId(): Int =
        (1..1000000).random()


    fun getUser(): Single<User> =
        retrofitService.userService
            .getUser()
            .subscribeOn(Schedulers.io())
            .map { Mapper.toUser(it) }
            .doOnError { throwable ->
                throwable.message?.let { Log.d("MAIN_DEBUG", "FAIL: User hasn't been downloaded, ERROR: $it") }
            }

    fun getProductList(): Single<List<Product>> =
        retrofitService.productsService
            .getProductList()
            .subscribeOn(Schedulers.io())
            .map{ Mapper.toProductList(it)}
            .doOnError { throwable ->
                throwable.message?.let { Log.d("MAIN_DEBUG", "FAIL: Products list hasn't been downloaded, ERROR: $it") }
            }


    fun getProductById(id: Int): Single<Product> =
        retrofitService.productsService
            .getProductByID(id)
            .subscribeOn(Schedulers.io())
            .map { Mapper.toProduct(it) }
            .doOnError { throwable ->
                throwable.message?.let { Log.d( "MAIN_DEBUG", "FAIL: Product with ID($id) hasn't been downloaded, ERROR: $it") }
            }
}
