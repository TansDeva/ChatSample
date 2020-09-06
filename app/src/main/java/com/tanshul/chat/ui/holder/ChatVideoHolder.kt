package com.tanshul.chat.ui.holder

import com.tanshul.chat.base.BaseChatHolder
import com.tanshul.chat.helper.handler.VideoPlayer.resume
import com.tanshul.chat.helper.handler.VideoPlayer.startPlayback
import com.tanshul.chat.model.ChatModel
import com.tanshul.chat.ui.databinding.RowChatVideoBinding

class ChatVideoHolder(
    private val binding: RowChatVideoBinding
) : BaseChatHolder(binding.root) {

    override fun bindData(chatModel: ChatModel) {
        setupLayout(chatModel, binding.chatItem, binding.chatText, binding.chatTime)
        chatModel.resourceId?.run {
            startPlayback(binding.chatVideo, chatModel)
        }
        binding.chatVideo.setOnClickListener {
            resume(binding.chatVideo, chatModel)
        }
    }
}