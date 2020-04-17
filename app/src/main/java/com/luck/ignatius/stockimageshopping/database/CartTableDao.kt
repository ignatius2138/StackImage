package com.luck.ignatius.stockimageshopping.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartTableDao {
    @Insert
    fun insert(cartItem: CartTable)

    @Query("DELETE FROM cart_contents")
    fun clear()

    @Query("SELECT * FROM cart_contents")
    fun getAllCartItems(): LiveData<List<CartTable>>

}