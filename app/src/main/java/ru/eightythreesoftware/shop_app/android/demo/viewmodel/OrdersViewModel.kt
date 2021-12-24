package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eightythreesoftware.shop_app.android.demo.model.Order
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.model.Repository
import ru.eightythreesoftware.shop_app.android.demo.model.User

class OrdersViewModel(private val repository: Repository): ViewModel(){

    private val _orders: MutableLiveData<List<Order>> by lazy {
       MutableLiveData<List<Order>>()
    }

    val orders: LiveData<List<Order>>
        get() = _orders

    fun loadUserOrders(userId: Int){
        try {
            repository.getUserOrdersById(userId)
                .subscribe { orders, error ->
                    if (orders.isNullOrEmpty()){
                        Log.d("MAIN_DEBUG", "FAIL: Data download error ERROR: ${error.message}")
                    }else{
                        _orders.postValue(orders)
                    }
                }
        }catch (throwable: Throwable){
            Log.d("MAIN_DEBUG", "ERROR: ${throwable.message.toString()}")
        }
        Log.d("MAIN_DEBUG","Current user orders view model items list: ${orders.value}")
    }
}