package com.zhuzichu.android.shared.databinding.imageview

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter(value = ["url", "fadeDuration", "error"], requireAll = false)
fun bindImageViewByUrl(imageView: ImageView, url: Any, fadeDuration: Int, @DrawableRes error: Int) {
    Glide.with(imageView)
        .load(url).also {
            if (fadeDuration != 0) {
                it.transition(DrawableTransitionOptions.withCrossFade(fadeDuration))
            }
        }
        .error(error)
        .into(imageView)
}