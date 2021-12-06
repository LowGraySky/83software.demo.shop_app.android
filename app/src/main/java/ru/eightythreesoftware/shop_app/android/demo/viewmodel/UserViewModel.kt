package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.eightythreesoftware.shop_app.android.demo.model.Repository
import ru.eightythreesoftware.shop_app.android.demo.model.User

class UserViewModel(
    private val repository: Repository,
    ): ViewModel() {

    private val _user: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            loadUser()
        }
    }

    val user: LiveData<User>
        get() = _user

    private fun loadUser(){
        repository.getUser()
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: user hasn't been added, ERROR: $throwable") }
            .subscribe { user ->
                _user.postValue(user)
            }.also {
                Log.d("MAIN_DEBUG", "SUCCESS: Data added to view model (user )")
            }
    }

    fun changeProfile(email: String, password: String){
        repository.getUser(email, password)
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: user hasn't been added, ERROR: $throwable") }
            .subscribe { user ->
                _user.postValue(user)
            }.also {
                Log.d("MAIN_DEBUG", "SUCCESS: Data added to view model (user )")
            }
    }
}