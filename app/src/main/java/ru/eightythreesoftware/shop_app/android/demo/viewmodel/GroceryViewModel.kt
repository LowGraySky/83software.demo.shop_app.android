package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eightythreesoftware.shop_app.android.demo.model.Product

class GroceryViewModel: ViewModel() {

    private val _grocery: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>()
    }

    val grocery: LiveData<List<Product>>
        get() = _grocery

    fun addToGrocery(product: Product){
        try {
            val resultList = mutableListOf<Product>()
            (grocery.value as Collection<Product>?)?.let { resultList.addAll(0, it) }
            resultList.add(product)
            _grocery.postValue(resultList)
        }catch (throwable: Throwable){
            Log.d("MAIN_DEBUG", "ERROR: ${throwable.message.toString()}")
        }
        Log.d("MAIN_DEBUG","Current grocery view model items list: ${grocery.value}")
    }

    fun removeFromGrocery(product: Product){
        try {
            val resultList = mutableListOf<Product>()
            (grocery.value as Collection<Product>?)?.let { resultList.addAll(0, it) }
            resultList.remove(product)
            _grocery.postValue(resultList)
        }catch (throwable: Throwable){
            Log.d("MAIN_DEBUG", "ERROR: ${throwable.message.toString()}")
        }
        Log.d("MAIN_DEBUG","Current grocery view model items list: ${grocery.value}")
    }
}