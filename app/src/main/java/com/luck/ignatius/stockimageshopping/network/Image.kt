package com.luck.ignatius.stockimageshopping.network

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