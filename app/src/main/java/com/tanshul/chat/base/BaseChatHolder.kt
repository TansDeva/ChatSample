package com.tanshul.chat.base

import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanshul.chat.helper.ChatFrom
import com.tanshul.chat.helper.ChatValues.getTimeFormat
import com.tanshul.chat.helper.show
import com.tanshul.chat.model.ChatModel
import com.tanshul.chat.ui.R
import com.tanshul.chat.ui.activity.ChatActivity

open class BaseChatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun bindData(chatModel: ChatModel) {
    }

    fun setupLayout(chatModel: ChatModel, layout: ViewGroup, chatText: TextView, chatTime: TextView) {
        layout.setBackgroundResource(if (chatModel.isMine) R.drawable.bg_chat_mine else R.drawable.bg_chat_other)
        layout.layoutParams = (layout.layoutParams as RelativeLayout.LayoutParams).apply {
            width = ChatActivity.layoutWidth
            if (chatModel.isMine) {
                addRule(RelativeLayout.ALIGN_PARENT_END)
            } else {
                removeRule(RelativeLayout.ALIGN_PARENT_END)
            }
        }
        setChatText(chatText, chatModel.text)
        chatTime.text = getTimeFormat(chatModel.timeStamp)
    }

    private fun setChatText(chatText: TextView, value: String?) {
        with(chatText) {
            value?.let {
                show(true)
                text = it
            } ?: run {
                show(false)
            }
        }
    }
}