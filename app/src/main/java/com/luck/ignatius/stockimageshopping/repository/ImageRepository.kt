package com.luck.ignatius.stockimageshopping.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase
import com.luck.ignatius.stockimageshopping.database.asModelImage
import com.luck.ignatius.stockimageshopping.domain.ModelImage
import com.luck.ignatius.stockimageshopping.network.StackPhotoApi
import com.luck.ignatius.stockimageshopping.network.asDatabaseImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepository(private val database: StackImageDatabase) {
    val images: LiveData<List<ModelImage>> = Transformations.map(database.databaseImageTableDao.getImagesFromDatabase()) {
        it.asModelImage()
    }

    suspend fun refreshImages() {
        withContext(Dispatchers.IO) {
            val imagesFeed = StackPhotoApi.retrofitService.getImages(null, null, null, "newest").await()
            database.databaseImageTableDao.insertAll(imagesFeed.asDatabaseImage())
        }
    }
}