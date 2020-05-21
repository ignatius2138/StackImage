package com.luck.ignatius.stockimageshopping.cart

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.luck.ignatius.stockimageshopping.database.CartTableDao
import kotlinx.coroutines.*


class CartViewModel(val database: CartTableDao, application: Application): AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)!!
    private val _accountName:MutableLiveData<String> = MutableLiveData(sharedPreferences.getString("accountName", "not logged in")!!)
    val accountName: LiveData<String>
        get() = _accountName
    private val isLoggedIn= sharedPreferences.getBoolean("isLoggedIn", false)
    private val _navigateToCart = MutableLiveData(false)
    val navigateToCart: LiveData<Boolean>
        get() = _navigateToCart


    private val mutableLiveData = MutableLiveData(1)
    val cartItems = Transformations.switchMap(mutableLiveData) {
        when(it) {
            1 -> database.getCartItemsByStatusAndAccount("cart", _accountName.value!!)
            else -> database.getCartItemsByStatusAndAccount("bought", _accountName.value!!)
        }
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun onCartItemClicked(cartItemId: String){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.deleteItemById(cartItemId)
                Log.i("delete", cartItemId)
            }
        }
    }

    fun buyItems() {
        if (isLoggedIn) {
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    database.updateStatus("bought")
                    database.updateAccount(_accountName.value!!, "cart")
                    Log.i("bought", _accountName.value!!)
                }
            }
        } else _navigateToCart.value = true
    }

    fun showPurchaseHistory(){
        mutableLiveData.value = 2
    }

    fun showCart(){
        mutableLiveData.value = 1
    }

    fun clearCart(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.clearCart("cart")
                Log.i("clear", "Cart cleared")
            }
        }
    }

    fun doneNavigating(){
        _navigateToCart.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}