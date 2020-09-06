package com.tanshul.chat.model

import com.tanshul.chat.helper.ChatFrom
import com.tanshul.chat.helper.ChatType
import com.tanshul.chat.helper.utils.AppUtils.getTimeStamp

data class ChatModel(
    val type: ChatType,
    val from: ChatFrom,
    val text: String? = null,
    val resourceId: Int? = null,
    val timeStamp: Long = getTimeStamp()
) {
    var isPlaying = false
    var isMine = from == ChatFrom.ME
}