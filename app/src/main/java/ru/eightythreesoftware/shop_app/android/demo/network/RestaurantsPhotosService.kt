package ru.eightythreesoftware.shop_app.android.demo.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.eightythreesoftware.shop_app.android.demo.network.restaurant_photos_response.RestaurantsPhotosResponse

interface RestaurantsPhotosService {

    @Headers("Authorization: 563492ad6f917000010000012933307d06264ff6a209513d653da8ec")
    @GET("v1/search?query=nature&per_page=10")
    fun getRestaurantsPhotos(): Single<RestaurantsPhotosResponse>
}