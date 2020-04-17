package com.luck.ignatius.stockimageshopping.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartTable::class], version = 1, exportSchema = false)
abstract class StackImageDatabase: RoomDatabase() {
    abstract val cartTableDao: CartTableDao

    companion object{
        @Volatile
        private var INSTANCE: StackImageDatabase? = null
        fun getInstance(context: Context): StackImageDatabase{
            synchronized(this) {
                var instance =  INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, StackImageDatabase::class.java, "stack_image_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}