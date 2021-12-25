package ru.eightythreesoftware.shop_app.android.demo.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.eightythreesoftware.shop_app.android.demo.network.user_response.UserResponse

interface UserService {

    @GET("users/random_user")
    fun getUser(): Single<UserResponse>

    @GET("users/random_user")
    fun getUser(email: String, password: String): Single<UserResponse>
}