package com.tanshul.chat.helper

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object AppConstants {
    const val SplashDelay = 1000L
}

object ChatValues {
    const val ChatWidthRatio = 0.7f
    val AddChatDelay = TimeUnit.SECONDS.toMillis(2)
    val DateFormatter = SimpleDateFormat("hh:mm a", Locale.ENGLISH)

    fun getTimeFormat(timeStamp: Long): String {
        return DateFormatter.format(Date(timeStamp))
    }
}