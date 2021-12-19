package ru.eightythreesoftware.shop_app.android.demo.model

import ru.eightythreesoftware.shop_app.android.demo.network.products_response.ProductResponse
import ru.eightythreesoftware.shop_app.android.demo.network.restaurant_photos_response.Photo
import ru.eightythreesoftware.shop_app.android.demo.network.restaurant_photos_response.PhotoSource
import ru.eightythreesoftware.shop_app.android.demo.network.restaurant_photos_response.RestaurantsPhotosResponse
import ru.eightythreesoftware.shop_app.android.demo.network.user_response.UserResponse

class Mapper {

    companion object {

        fun toRestaurantsList(response: RestaurantsPhotosResponse): List<Restaurant>{
            val resultList: MutableList<Restaurant> = mutableListOf()
            for(photo in response.photos){
                val mappedResponse = toRestaurant(photo)
                resultList.add(mappedResponse)
            }
            return resultList
        }

        fun toRestaurant(response: Photo): Restaurant{
            return Restaurant(
                response.photoSource.portrait
            )
        }

        fun toUser(response: UserResponse): User{
            return User(
                response.id,
                response.first_name,
                response.last_name,
                response.date_of_birth,
                response.address,
                response.email,
                response.avatar,
                response.phone_number,
                false
            )
        }


        fun toProduct(response: ProductResponse): Product{
            return Product(
                response.id,
                response.name,
                response.abv,
                response.image_url,
                response.ingredients,
                response.description,
                10.0
            )
        }

        fun toProductList(list: List<ProductResponse>): List<Product>{
            val resultList: MutableList<Product> = mutableListOf()
            for(product in list){
                val mappedResponse = toProduct(product)
                resultList.add(mappedResponse)
            }
            return resultList
        }
    }
}