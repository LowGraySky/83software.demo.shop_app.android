package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.eightythreesoftware.shop_app.android.demo.model.Repository
import ru.eightythreesoftware.shop_app.android.demo.model.Restaurant

class RestaurantsViewModel(private val repository: Repository): ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }


    private val _restaurants: MutableLiveData<List<Restaurant>> by lazy {
        MutableLiveData<List<Restaurant>>().also {
            loadRestaurants()
        }
    }

    val restaurant: LiveData<List<Restaurant>>
    get() = _restaurants

    private fun loadRestaurants(){
        repository
            .getRestaurants()
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: data hasn't been added , ERROR: $throwable")
            }
            .subscribe { restaurants ->
                _restaurants.postValue(restaurants)
            }.also {
                compositeDisposable.add(it)
                Log.d("MAIN_DEBUG", "SUCCESS: Data added to view model (restaurants view model)")
            }
    }


}