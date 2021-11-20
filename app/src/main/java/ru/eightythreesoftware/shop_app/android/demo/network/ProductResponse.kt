package ru.eightythreesoftware.shop_app.android.demo.network

data class ProductResponse(
    val abv: Double,
    val description: String,
    val id: Int,
    val image_url: String,
    val ingredients: String,
    val name: String,
)