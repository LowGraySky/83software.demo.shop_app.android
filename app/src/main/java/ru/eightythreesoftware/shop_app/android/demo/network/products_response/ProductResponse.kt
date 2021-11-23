package ru.eightythreesoftware.shop_app.android.demo.network.products_response

import ru.eightythreesoftware.shop_app.android.demo.network.products_response.BoilVolume
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.Ingredients
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.Method
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.Volume

data class ProductResponse(
    val abv: Double,
    val attenuation_level: Double,
    val boil_volume: BoilVolume,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Int,
    val first_brewed: String,
    val food_pairing: List<String>,
    val ibu: Double,
    val id: Int,
    val image_url: String,
    val ingredients: Ingredients,
    val method: Method,
    val name: String,
    val ph: Double,
    val srm: Double,
    val tagline: String,
    val target_fg: Int,
    val target_og: Double,
    val volume: Volume
)