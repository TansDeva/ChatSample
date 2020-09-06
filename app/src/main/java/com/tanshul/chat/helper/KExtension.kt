package com.tanshul.chat.helper

import android.view.View

fun Exception.logException() {
    printStackTrace()
}

fun View.show(isShow: Boolean) {
    visibility = if (isShow) View.VISIBLE else View.GONE
}