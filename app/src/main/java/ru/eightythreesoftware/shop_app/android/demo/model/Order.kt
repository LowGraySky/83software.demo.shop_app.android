package ru.eightythreesoftware.shop_app.android.demo.model

import java.util.*

class Order(
    var orderId: Int,
    var time: Date,
    var date: Date,
    var productsInOrder: List<Product>,
    var orderPrice: Double,
    var orderAddress: String,
    var userId: Int
)