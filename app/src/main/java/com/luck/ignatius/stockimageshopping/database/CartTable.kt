package com.luck.ignatius.stockimageshopping.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_contents")
data class CartTable(
    @PrimaryKey
    var url: String = "empty Url",
    @ColumnInfo
    var price: Int = -1,
    @ColumnInfo
    var description: String = "empty description") {
}