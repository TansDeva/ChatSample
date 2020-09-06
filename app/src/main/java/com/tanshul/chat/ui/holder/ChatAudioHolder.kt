package com.tanshul.chat.ui.holder

import com.tanshul.chat.base.BaseChatHolder
import com.tanshul.chat.helper.AdapterCallback
import com.tanshul.chat.helper.handler.MusicPlayer.pause
import com.tanshul.chat.helper.handler.MusicPlayer.startPlayback
import com.tanshul.chat.model.ChatModel
import com.tanshul.chat.ui.databinding.RowChatAudioBinding

class ChatAudioHolder(
    private val binding: RowChatAudioBinding
) : BaseChatHolder(binding.root) {
    var isPlaying = false
    lateinit var callback: AdapterCallback

    override fun bindData(chatModel: ChatModel) {
        setupLayout(chatModel, binding.chatItem, binding.chatText, binding.chatTime)
        updateStatus(chatModel.isPlaying)
        binding.chatPlay.setOnClickListener {
            if (isPlaying) {
                pause()
            } else {
                startPlayback(it.context, chatModel, callback)
            }
            isPlaying = !isPlaying
            updateStatus(isPlaying)
        }
    }

    private fun updateStatus(playing: Boolean) {
        isPlaying = playing
        binding.chatPlay.setImageResource(if (isPlaying) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play)
    }
}