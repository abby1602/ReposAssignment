package com.repos.repoassignment.utils

import androidx.databinding.BindingAdapter

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.repos.repoassignment.R

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, imageUrl: Any?) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(App.instance.context.getDrawable(R.drawable.reposlogo))
        .into(view)
}