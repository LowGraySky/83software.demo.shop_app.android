package ru.eightythreesoftware.shop_app.android.demo.network.restaurant_photos_response

data class RestaurantsPhotosResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)