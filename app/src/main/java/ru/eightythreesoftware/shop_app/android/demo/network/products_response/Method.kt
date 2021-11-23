package ru.eightythreesoftware.shop_app.android.demo.network.products_response

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: String
)