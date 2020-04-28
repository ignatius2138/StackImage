package com.luck.ignatius.stockimageshopping.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseImageTableDao {
    @Query("SELECT * from databaseimage")
    fun getImagesFromDatabase(): LiveData<List<DatabaseImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<DatabaseImage>)
}