package com.example.catapiapp.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.catapiapp.utils.loadImage

class CatDataBinding {

    companion object {

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            imageView.loadImage(url)
        }
    }

}