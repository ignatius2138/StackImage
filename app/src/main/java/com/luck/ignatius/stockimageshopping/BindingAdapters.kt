package com.luck.ignatius.stockimageshopping

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luck.ignatius.stockimageshopping.mainscreen.ImageGridAdapter
import com.luck.ignatius.stockimageshopping.mainscreen.StackPhotoApiStatus
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

@BindingAdapter("stackPhotoApiStatus")
fun bindStatus(statusImageView: ImageView, status: StackPhotoApiStatus?) {
    when (status) {
        StackPhotoApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        StackPhotoApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        StackPhotoApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

