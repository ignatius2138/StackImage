package com.luck.ignatius.stockimageshopping.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Image(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val search_id: String,
    val total_count: Int
)

@Parcelize
data class Data(
    val aspect: Double,
    val assets: Assets,
    val contributor: Contributor,
    val description: String,
    val has_model_release: Boolean,
    val id: String,
    val image_type: String,
    val media_type: String
) : Parcelable

@Parcelize
data class Assets(
    val huge_thumb: HugeThumb,
    val large_thumb: LargeThumb,
    val preview: Preview,
    val preview_1000: Preview1000,
    val preview_1500: Preview1500,
    val small_thumb: SmallThumb
) : Parcelable

@Parcelize
data class Contributor(
    val id: String
) : Parcelable

@Parcelize
data class HugeThumb(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable

@Parcelize
data class LargeThumb(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable

@Parcelize
data class Preview(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable

@Parcelize
data class Preview1000(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable

@Parcelize
data class Preview1500(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable

@Parcelize
data class SmallThumb(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable