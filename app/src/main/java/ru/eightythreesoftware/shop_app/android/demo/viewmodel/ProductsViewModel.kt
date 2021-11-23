package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import ru.eightythreesoftware.shop_app.android.demo.model.Repository
import ru.eightythreesoftware.shop_app.android.demo.network.products_response.ProductResponse

class ProductsViewModel(private val repository: Repository): ViewModel() {

    val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _productsList: MutableLiveData<List<ProductResponse>> by lazy {
        MutableLiveData<List<ProductResponse>>().also {
            loadProductsList()
        }
    }

    val productsList: LiveData<List<ProductResponse>>
        get() = _productsList

    private fun loadProductsList(){
        repository.getProductList()
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: data hasn't been added , ERROR: $throwable")
            }
            .subscribe { products ->
                _productsList.postValue(products)
            }.also {
                compositeDisposable.add(it)
                Log.d("MAIN_DEBUG", "SUCCESS: Data added to view model (products list)")
            }
    }

    fun loadProductById(id: Int){
        repository.getProductById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: data hasn't been added , ERROR: $throwable")
            }.subscribe { value ->
                System.out.println(value)
            }.also {
                compositeDisposable.add(it)
                Log.d("MAIN_DEBUG", "SUCCESS: Data added to view model (product with id: $id)")
            }
    }

    fun stop(){
        compositeDisposable.clear()
    }

}
