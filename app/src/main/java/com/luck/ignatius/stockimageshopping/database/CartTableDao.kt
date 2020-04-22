package com.luck.ignatius.stockimageshopping.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface CartTableDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cartItem: CartTable)

    @Query("DELETE FROM cart_contents WHERE itemStatus = :cart")
    fun clearCart(cart: String)

    @Query("SELECT * FROM cart_contents")
    fun getAllCartItems(): LiveData<List<CartTable>>

    @Query("SELECT * FROM cart_contents WHERE itemStatus = :itemStatus")
    fun getCartItemsByStatus(itemStatus: String): LiveData<List<CartTable>>

    @Query("DELETE FROM cart_contents WHERE url = :cartItemId")
    fun deleteItemById(cartItemId: String)

    @Query("UPDATE cart_contents SET itemStatus = :newStatus")
    fun updateStatus(newStatus: String)

    @Query("UPDATE cart_contents SET accountName = :accountName")
    fun updateAccount(accountName: String)
}