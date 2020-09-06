package com.tanshul.chat.helper.handler

import android.content.Context
import android.media.MediaPlayer
import com.tanshul.chat.helper.AdapterCallback
import com.tanshul.chat.model.ChatModel

object MusicPlayer {
    var chatModel: ChatModel? = null
    var mediaPlayer: MediaPlayer? = null
    var listener: AdapterCallback? = null

    fun startPlayback(context: Context, model: ChatModel, callback: AdapterCallback) {
        VideoPlayer.stopPlayback()
        if (chatModel?.resourceId == model.resourceId) {
            mediaPlayer?.run {
                start()
                submitCallback(true)
            }
        } else {
            stopPlayback()
            chatModel = model
            listener = callback
            mediaPlayer = MediaPlayer.create(context, chatModel?.resourceId!!).apply {
                setOnPreparedListener {
                    if (chatModel?.resourceId == model.resourceId) {
                        start()
                        submitCallback(true)
                    }
                }
                setOnSeekCompleteListener {
                    submitCallback(false)
                }
            }
        }
    }

    fun pause() {
        mediaPlayer?.pause()
    }

    fun stopPlayback() {
        submitCallback(false)
        mediaPlayer?.run {
            stop()
            release()
        }
        mediaPlayer = null
        chatModel = null
    }

    private fun submitCallback(isPlaying: Boolean) {
        chatModel?.run {
            listener?.onSubmit(timeStamp, isPlaying)
        }
    }
}