package com.zhuzichu.android.shared.databinding.imageview

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pixplicity.sharp.Sharp

@BindingAdapter(value = ["url", "fadeDuration", "error"], requireAll = false)
fun bindImageViewByUrl(
    imageView: ImageView,
    url: Any?,
    fadeDuration: Int, @DrawableRes error: Int
) {
    url?.apply {
        Glide.with(imageView)
            .load(this).also {
                if (fadeDuration != 0) {
                    it.transition(DrawableTransitionOptions.withCrossFade(fadeDuration))
                }
            }
            .error(error)
            .into(imageView)
    }
}

@BindingAdapter(value = ["svgData"], requireAll = false)
fun bindImageViewShap(
    imageView: ImageView,
    svgData: String?
) {
    svgData?.let {
        Sharp.loadString(it).into(imageView)
    }
}