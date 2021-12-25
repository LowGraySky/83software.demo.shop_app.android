package ru.eightythreesoftware.shop_app.android.demo.model

import android.util.Log
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.eightythreesoftware.shop_app.android.demo.network.RetrofitService
import java.time.LocalTime

class Repository(private val retrofitService: RetrofitService) {

    private fun generateRandomId(): Int =
        (1..1000000).random()

    fun getUserOrdersById(userId: Int): Single<List<Order>> =
        Single.fromCallable {
            listOf(
                Order(
                  generateRandomId(),
                  LocalTime.now(),
                  listOf(
                      Product(generateRandomId(), "Motueka", 4.5 , "", null, "", 10.0),
                      Product(generateRandomId(), "Buzz", 4.5 , "", null, "", 10.0),
                      Product(generateRandomId(), "Amarillo", 4.5 , "", null, "", 10.0)),
                  1000.0,
                    "Moscow ulica Pushkina 10 kvartira 3",
                    userId
                ),
                Order(
                    generateRandomId(),
                    LocalTime.now(),
                    listOf(
                        Product(generateRandomId(), "Buzz", 4.5 , "", null, "", 10.0),
                        Product(generateRandomId(), "Amarillo", 4.5 , "", null, "", 10.0),
                        Product(generateRandomId(), "Buzz", 4.5 , "", null, "", 10.0)),
                    127.90,
                    "Moscow ulica Pushkina 10 kvartira 3",
                    userId
                ),
                Order(
                    generateRandomId(),
                    LocalTime.now(),
                    listOf(
                        Product(generateRandomId(), "Motueka", 4.5 , "", null, "", 10.0),
                        Product(generateRandomId(), "Buzz", 4.5 , "", null, "", 10.0),
                        Product(generateRandomId(), "Buzz", 4.5 , "", null, "", 10.0)),
                    560.0,
                    "Moscow ulica Pushkina 10 kvartira 3",
                    userId
                ),
            )
        }

    fun getUser(email: String, password: String): Single<User> =
        retrofitService.userService
            .getUser()
            .subscribeOn(Schedulers.io())
            .map { response ->
                Log.d("MAIN_DEBUG", "INFO: Downloaded user with email:${email}, Data: ${response.toString()}")
                Mapper.toUser(response)
            }
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: User hasn't been downloaded, ERROR: ${throwable.printStackTrace()}")
            }

    fun getUser(): Single<User> =
        retrofitService.userService
            .getUser()
            .subscribeOn(Schedulers.io())
            .map { response ->
                Log.d("MAIN_DEBUG", "INFO: Downloaded user, Data: ${response.toString()}")
                Mapper.toUser(response)
            }
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: User hasn't been downloaded, ERROR: ${throwable.printStackTrace()}")
            }

    fun getProductList(): Single<List<Product>> =
        retrofitService.productsService
            .getProductList()
            .subscribeOn(Schedulers.io())
            .map{ response ->
                Log.d("MAIN_DEBUG", "INFO: Downloaded products list, Data: ${response.toString()}")
                Mapper.toProductList(response)
            }
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: Products list hasn't been downloaded, ERROR: ${throwable.printStackTrace()}")
            }


    fun getProductById(id: Int): Single<Product> =
        retrofitService.productsService
            .getProductByID(id)
            .subscribeOn(Schedulers.io())
            .map { response ->
                Log.d("MAIN_DEBUG", "INFO: Downloaded product with id: $id, Data: ${response.toString()}")
                Mapper.toProduct(response)
            }
            .doOnError { throwable ->
                Log.d( "MAIN_DEBUG", "FAIL: Product with ID($id) hasn't been downloaded, ERROR: ${throwable.printStackTrace()}")
            }

    fun getRestaurants(): Single<List<Restaurant>> =
        retrofitService
            .restaurantsPhotosService
            .getRestaurantsPhotos(1, 10)
            .subscribeOn(Schedulers.io())
            .map { response ->
                Log.d("MAIN_DEBUG", "INFO: Downloaded restaurants list, Data: ${response.map { it.toString() }}")
                Mapper.toRestaurantsList(response)
            }
            .doOnError { throwable ->
                Log.d( "MAIN_DEBUG", "FAIL: Restaurants photos hasn't been downloaded, ERROR: ${throwable.printStackTrace()}")
            }
}
