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
            val elements: MutableList<Product>? = grocery.value as MutableList<Product>?
            elements?.add(product)
            _grocery.postValue(elements)
        }catch (throwable: Throwable){
            Log.d("MAIN_DEBUG", "ERROR: ${throwable.message.toString()}")
        }
    }

    fun removeFromGrocery(product: Product){
        try {
            val elements: MutableList<Product>? = grocery.value as MutableList<Product>?
            elements?.remove(product)
            _grocery.postValue(elements)
        }catch (throwable: Throwable){
            Log.d("MAIN_DEBUG", "ERROR: ${throwable.message.toString()}")
        }
    }
}