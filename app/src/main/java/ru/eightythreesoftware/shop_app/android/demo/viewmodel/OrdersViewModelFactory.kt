package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.eightythreesoftware.shop_app.android.demo.model.Repository

class OrdersViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrdersViewModel(repository) as T
    }
}