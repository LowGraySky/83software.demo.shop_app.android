package ru.eightythreesoftware.shop_app.android.demo.model

import ru.eightythreesoftware.shop_app.android.demo.network.products_response.ProductResponse

class Mapper {

    companion object {

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