package com.tanshul.chat.helper

import android.view.View
import com.tanshul.chat.BuildConfig

fun Exception.logException() {
    if (BuildConfig.DEBUG) {
        printStackTrace()
    }
}

fun View.show(isShow: Boolean) {
    visibility = if (isShow) View.VISIBLE else View.GONE
}