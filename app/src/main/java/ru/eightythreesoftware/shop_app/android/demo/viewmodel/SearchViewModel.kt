package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eightythreesoftware.shop_app.android.demo.model.Repository

class SearchViewModel(private val repository: Repository): ViewModel() {

    private val _searchableQuery: MutableLiveData<Any> by lazy {
        MutableLiveData<Any>()
    }

    val searchableQuery: LiveData<Any>
        get() = _searchableQuery

    private fun processSearch(){

    }

}