package com.luck.ignatius.stockimageshopping.network

import com.squareup.moshi.Json

/*
data class Image(
    val `data`: List<Data>,
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "search_id")
    val searchId: String,
    @Json(name = "total_count")
    val totalCount: Int
)

data class Data(
    val aspect: Double,
    val assets: Assets,
    val contributor: Contributor,
    val description: String,
    val has_model_release: Boolean,
    val id: String,
    @Json(name = "image_type")
    val imageType: String,
    @Json(name = "media_type")
    val mediaType: String
)

data class Assets(
    @Json(name = "huge_thumb")
    val hugeThumb: HugeThumb,
    @Json(name = "large_thumb")
    val largeThumb: LargeThumb,
    val preview: Preview,
    @Json(name = "preview_1000")
    val preview1000: Preview1000,
    @Json(name = "preview_1500")
    val preview1500: Preview1500,
    @Json(name = "small_thumb")
    val smallThumb: SmallThumb
)

data class Contributor(
    val id: String
)

data class HugeThumb(
    val height: Int,
    val url: String,
    val width: Int
)

data class LargeThumb(
    val height: Int,
    val url: String,
    val width: Int
)

data class Preview(
    val height: Int,
    val url: String,
    val width: Int
)

data class Preview1000(
    val height: Int,
    val url: String,
    val width: Int
)

data class Preview1500(
    val height: Int,
    val url: String,
    val width: Int
)

data class SmallThumb(
    val height: Int,
    val url: String,
    val width: Int
)*/
data class Image(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val search_id: String,
    val total_count: Int
)

data class Data(
    val aspect: Double,
    val assets: Assets,
    val contributor: Contributor,
    val description: String,
    val has_model_release: Boolean,
    val id: String,
    val image_type: String,
    val media_type: String
)

data class Assets(
    val huge_thumb: HugeThumb,
    val large_thumb: LargeThumb,
    val preview: Preview,
    val preview_1000: Preview1000,
    val preview_1500: Preview1500,
    val small_thumb: SmallThumb
)

data class Contributor(
    val id: String
)

data class HugeThumb(
    val height: Int,
    val url: String,
    val width: Int
)

data class LargeThumb(
    val height: Int,
    val url: String,
    val width: Int
)

data class Preview(
    val height: Int,
    val url: String,
    val width: Int
)

data class Preview1000(
    val height: Int,
    val url: String,
    val width: Int
)

data class Preview1500(
    val height: Int,
    val url: String,
    val width: Int
)

data class SmallThumb(
    val height: Int,
    val url: String,
    val width: Int
)