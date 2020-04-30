package com.luck.ignatius.stockimageshopping.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_contents")
data class CartTable(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,
    @ColumnInfo
    var url: String = "empty Url",
    @ColumnInfo
    var price: Int = -1,
    @ColumnInfo
    var description: String = "empty description",
    @ColumnInfo
    var accountName: String = "Not logged in") {
    @ColumnInfo
    var itemStatus: String = "cart"

}