package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eightythreesoftware.shop_app.android.demo.model.Order
import ru.eightythreesoftware.shop_app.android.demo.model.Repository

class OrdersViewModel(private val repository: Repository): ViewModel(){

    private val _orders: MutableLiveData<List<Order>> by lazy {
       MutableLiveData<List<Order>>()
    }


    val orders: LiveData<List<Order>>
        get() = _orders

}