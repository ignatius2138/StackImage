package com.luck.ignatius.stockimageshopping

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
    fun bindImg(imgView: ImageView, imgUrl: String?) {
    imgUrl.let {
        Picasso.get().load(imgUrl).into(imgView)
    }
}
