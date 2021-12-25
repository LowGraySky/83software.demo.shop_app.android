package ru.eightythreesoftware.shop_app.android.demo.network.user_response

data class Subscription(
    val payment_method: String,
    val plan: String,
    val status: String,
    val term: String
)