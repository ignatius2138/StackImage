package com.luck.ignatius.stockimageshopping

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luck.ignatius.stockimageshopping.mainscreen.ImageGridAdapter
import com.luck.ignatius.stockimageshopping.network.Data
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImg(imgView: ImageView, imgUrl: String?) {
    imgUrl.let {
        Picasso.get().load(imgUrl).placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image).into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Data>?) {
    val adapter = recyclerView.adapter as ImageGridAdapter
    adapter.submitList(data)
}

