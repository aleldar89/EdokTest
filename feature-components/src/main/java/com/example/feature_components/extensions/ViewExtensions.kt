package com.example.feature_components.extensions

import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.example.feature_components.glide.GlideApp
import com.example.ui_components.R

fun ImageView.loadImage(url: String, vararg transforms: BitmapTransformation = emptyArray()) =
    GlideApp.with(this)
        .load(url)
        .placeholder(R.drawable.ic_error_24)
        .error(R.drawable.ic_error_24)
        .transform(*transforms)
        .timeout(30_000)
        .into(this)

fun ImageView.loadImage(vararg transforms: BitmapTransformation = emptyArray()) =
    GlideApp.with(this)
        .load(R.drawable.ic_loading_24)
        .placeholder(R.drawable.ic_loading_24)
        .error(R.drawable.ic_error_24)
        .transform(*transforms)
        .timeout(30_000)
        .into(this)