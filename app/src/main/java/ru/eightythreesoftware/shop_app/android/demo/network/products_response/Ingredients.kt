package ru.eightythreesoftware.shop_app.android.demo.network.products_response

data class Ingredients(
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)