package com.luck.ignatius.stockimageshopping.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.luck.ignatius.stockimageshopping.database.CartTableDao
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase

class CartViewModel(val database: CartTableDao, application: Application): AndroidViewModel(application) {
    val cartItems = database.getAllCartItems()
}