package com.example.mvvmarchitecture.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(url:String?){
    Glide.with(this).load(url).fitCenter().into(this)
}