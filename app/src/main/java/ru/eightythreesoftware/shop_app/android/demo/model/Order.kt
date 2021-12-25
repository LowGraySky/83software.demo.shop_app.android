package ru.eightythreesoftware.shop_app.android.demo.model

import java.time.LocalTime
import java.util.*

class Order(
    var orderId: Int,
    var dateTime: LocalTime,
    var productsInOrder: List<Product>,
    var orderPrice: Double,
    var orderAddress: String,
    var userId: Int
)