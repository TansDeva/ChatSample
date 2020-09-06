package com.tanshul.chat.ui.holder

import android.graphics.BitmapFactory
import com.tanshul.chat.base.BaseChatHolder
import com.tanshul.chat.databinding.RowChatImageBinding
import com.tanshul.chat.model.ChatModel

class ChatImageHolder(
    private val binding: RowChatImageBinding
) : BaseChatHolder(binding.root) {

    override fun bindData(chatModel: ChatModel) {
        setupLayout(chatModel, binding.chatItem, binding.chatText, binding.chatTime)
        chatModel.resourceId?.let { resId ->
            with(binding.chatImage) {
                val imageStream = context.resources.openRawResource(resId)
                val bitmap = BitmapFactory.decodeStream(imageStream)
                setImageBitmap(bitmap)
            }
        }
    }
}