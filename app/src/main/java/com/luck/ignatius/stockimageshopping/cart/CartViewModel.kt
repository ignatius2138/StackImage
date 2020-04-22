package com.luck.ignatius.stockimageshopping.cart

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.luck.ignatius.stockimageshopping.database.CartTableDao
import kotlinx.coroutines.*


class CartViewModel(val database: CartTableDao, application: Application): AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)!!
    private var accountName = sharedPreferences.getString("accountName", "not logged in")!!
    private val mutableLiveData = MutableLiveData(1)
    val cartItems = Transformations.switchMap(mutableLiveData) {
        when(it) {
            1 -> database.getCartItemsByStatus("cart")
            else -> database.getCartItemsByStatus("bought")
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

    fun buyItems(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.updateStatus("bought")
                database.updateAccount(accountName)
                Log.i("bought", accountName)
            }
        }
    }

    fun showPurchaseHistory(){
        mutableLiveData.value = 2
    }

    fun clearCart(){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                database.clearCart("cart")
                Log.i("clear", "Cart cleared")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}