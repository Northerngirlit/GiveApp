package com.truecaller.giveapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


////////////////////ViewGroup//////////////////////////

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

////////////////////ImageView//////////////////////////

fun ImageView.loadUrl(url: String?) {
    //TODO load image
}