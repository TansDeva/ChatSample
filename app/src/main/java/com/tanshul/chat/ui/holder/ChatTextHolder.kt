package com.tanshul.chat.ui.holder

import com.tanshul.chat.base.BaseChatHolder
import com.tanshul.chat.model.ChatModel
import com.tanshul.chat.ui.databinding.RowChatTextBinding

class ChatTextHolder(
    private val binding: RowChatTextBinding
) : BaseChatHolder(binding.root) {

    override fun bindData(chatModel: ChatModel) {
        setupLayout(chatModel, binding.chatItem, binding.chatText, binding.chatTime)
        chatModel.text?.run {
            binding.chatText.text = this
        }
    }
}