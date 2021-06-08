package com.h5190067.saatsaatcloneapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object GildeUtil {
    fun glideImage(context: Context, url: String, image: ImageView) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(image)
    }
}