package ru.eightythreesoftware.shop_app.android.demo.model

import ru.eightythreesoftware.shop_app.android.demo.network.user_response.Address

class User(
    var userId: Int,
    var firstName: String,
    var lastName: String,
    var dateOfBirthday: String,
    var userAddress: Address,
    var email: String,
    var image: String,
    var phone: String
)