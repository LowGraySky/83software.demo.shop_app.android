package ru.eightythreesoftware.shop_app.android.demo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.eightythreesoftware.shop_app.android.demo.model.Product
import ru.eightythreesoftware.shop_app.android.demo.model.Repository

class ProductsViewModel(private val repository: Repository): ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _productsList: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>().also {
            loadProductsList()
        }
    }

    val productsList: LiveData<List<Product>>
        get() = _productsList

    private val _singleProduct: MutableLiveData<Product> by lazy {
        MutableLiveData<Product>()
    }

    val singleProduct: LiveData<Product>
        get() = _singleProduct

    fun selectSingleProduct(product: Product){
        _singleProduct.postValue(product)
    }

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
            .doOnError { throwable ->
                Log.d("MAIN_DEBUG", "FAIL: data hasn't been added , ERROR: $throwable")
            }.subscribe { value ->
                _singleProduct.postValue(value)
            }.also {
                compositeDisposable.add(it)
                Log.d("MAIN_DEBUG", "SUCCESS: Data added to view model (product with id: $id)")
            }
    }

    fun stop(){
        compositeDisposable.clear()
    }

}
