package com.luck.ignatius.stockimageshopping.cart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luck.ignatius.stockimageshopping.database.CartTableDao
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase
import java.lang.IllegalArgumentException

class CartViewModelFactory(private val dataSource: CartTableDao, private val application: Application): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}