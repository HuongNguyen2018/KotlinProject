package com.hh.gitbub.util

import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CustomBinding {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImage(imageview: ImageView, url: String) {

            if (url !== null) {
                Glide.with(imageview.context)
                    .load(url)
                    .into(imageview)
            } else {
                imageview.setImageResource(android.R.drawable.picture_frame)
            }

        }
    }
