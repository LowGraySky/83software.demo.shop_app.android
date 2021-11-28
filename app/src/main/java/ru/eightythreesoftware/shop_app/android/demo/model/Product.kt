package ru.eightythreesoftware.shop_app.android.demo.model

import ru.eightythreesoftware.shop_app.android.demo.network.products_response.Ingredients

class Product(
    var id: Int,
    var name: String,
    var abv: Double,
    var image: String?,
    var ingredients: Ingredients?,
    var description: String?,
    var price: Double?
)