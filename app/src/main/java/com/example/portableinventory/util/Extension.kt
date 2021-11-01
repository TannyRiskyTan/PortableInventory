package com.example.portableinventory.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.portableinventory.presentation.activity.MainActivity

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Fragment.configureHomeButtonAsBack(flag: Boolean) {
    (requireActivity() as MainActivity).supportActionBar?.apply {
        if (flag) show() else hide()
    }
}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}
