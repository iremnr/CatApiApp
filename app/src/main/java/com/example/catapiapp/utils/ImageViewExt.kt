package com.example.catapiapp.utils

import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.catapiapp.R


fun ImageView.loadImage(url: String?) {
    val placeholder = createPlaceHolder(this.context)
    val options = com.bumptech.glide.request.RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

private fun createPlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius = 40f
        start()
    }
}

