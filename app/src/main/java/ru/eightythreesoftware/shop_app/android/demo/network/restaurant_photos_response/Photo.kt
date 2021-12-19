package ru.eightythreesoftware.shop_app.android.demo.network.restaurant_photos_response

data class Photo(
    val alt: String,
    val avg_color: String,
    val height: Int,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val photoSource: PhotoSource,
    val url: String,
    val width: Int
)