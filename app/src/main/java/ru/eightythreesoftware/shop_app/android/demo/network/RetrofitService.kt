package ru.eightythreesoftware.shop_app.android.demo.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private final val BASE_URL: String = "https://api.punkapi.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val service: NetworkService = retrofit.create(NetworkService::class.java)

    companion object{
        fun newInstance() = RetrofitService()
    }
}