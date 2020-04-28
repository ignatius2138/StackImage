package com.luck.ignatius.stockimageshopping.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.luck.ignatius.stockimageshopping.domain.ModelImage

@Entity
data class DatabaseImage(
    @PrimaryKey
    val hugeThumbUrl: String,
    val description: String,
    val id: String,
    val imageType: String
)

fun List<DatabaseImage>.asModelImage(): List<ModelImage> {
    return map {
        ModelImage(description = it.description, id = it.id, imageType = it.imageType, hugeThumbUrl = it.hugeThumbUrl)
    }
}