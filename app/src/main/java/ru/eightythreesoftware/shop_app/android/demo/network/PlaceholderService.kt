package ru.eightythreesoftware.shop_app.android.demo.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PlaceholderService {

    @GET("placeholdit/random_placeholdit")
    fun getPlaceholder(): Single<Placeholder>
}