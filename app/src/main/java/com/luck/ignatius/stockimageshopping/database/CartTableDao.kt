package com.luck.ignatius.stockimageshopping.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartTableDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cartItem: CartTable)

    @Query("DELETE FROM cart_contents WHERE itemStatus = :cart")
    fun clearCart(cart: String)

    @Query("SELECT * FROM cart_contents")
    fun getAllCartItems(): LiveData<List<CartTable>>

    @Query("SELECT * FROM cart_contents WHERE itemStatus = :itemStatus AND accountName = :accountName")
    fun getCartItemsByStatusAndAccount(itemStatus: String, accountName: String): LiveData<List<CartTable>>

    @Query("DELETE FROM cart_contents WHERE url = :cartItemId")
    fun deleteItemById(cartItemId: String)

    @Query("UPDATE cart_contents SET itemStatus = :newStatus")
    fun updateStatus(newStatus: String)

    @Query("UPDATE cart_contents SET accountName = :accountName WHERE itemStatus = :itemStatus")
    fun updateAccount(accountName: String, itemStatus: String)

    @Query("UPDATE cart_contents SET accountName = :accountName WHERE itemStatus = :itemStatus AND accountName = :notLoggedIn")
    fun updateNotLoggedInAccount(accountName: String, itemStatus: String, notLoggedIn: String)

    @Query("SELECT EXISTS(SELECT 1 FROM cart_contents WHERE url = :url AND accountName = :accountName)")
    fun checkIfImageAlreadyExists(url: String, accountName: String): Int

}