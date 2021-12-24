package ru.eightythreesoftware.shop_app.android.demo.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.eightythreesoftware.shop_app.android.demo.network.restaurant_photos_response.RestaurantsPhotosResponse

interface RestaurantsPhotosService {

    @GET("/v2/list")
    fun getRestaurantsPhotos(@Query("page") page: Int, @Query("limit") limit: Int): Single<List<RestaurantsPhotosResponse>>
}