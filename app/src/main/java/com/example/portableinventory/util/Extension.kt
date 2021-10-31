package com.example.portableinventory.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}
