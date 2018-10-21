package com.truecaller.giveapp.utils

import android.app.Activity
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
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

////////////////////Activity//////////////////////////

fun Activity.setStatusBarColor(color: Int) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = color
    }
}


fun AppCompatActivity.configToolbar(toolbar: Toolbar, displayHomeAsUpEnabled: Boolean, title: String? = null) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
    title?.let { supportActionBar?.title = it }

}