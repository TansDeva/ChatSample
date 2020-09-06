package com.tanshul.chat.helper.handler

import android.net.Uri
import android.widget.VideoView
import com.tanshul.chat.model.ChatModel

object VideoPlayer {
    private var isStarted = false
    private var videoView: VideoView? = null
    private var chatModel: ChatModel? = null

    fun startPlayback(view: VideoView, model: ChatModel, isStart: Boolean = false) {
        if (chatModel?.resourceId == model.resourceId) {
            videoView?.resume()
        } else {
            stopPlayback()
            chatModel = model
            chatModel?.resourceId?.let { resId ->
                videoView = view.apply {
                    val path = "android.resource://${context.packageName}/${resId}"
                    setVideoURI(Uri.parse(path))
                    if (isStart) {
                        start()
                    }
                }
            }
        }
    }

    fun resume(view: VideoView, model: ChatModel) {
        MusicPlayer.stopPlayback()
        videoView?.run {
            if (isStarted) {
                resume()
            } else {
                isStarted = true
                start()
            }
        } ?: run {
            startPlayback(view, model, true)
        }
    }

    fun stopPlayback() {
        isStarted = false
        videoView?.run {
            stopPlayback()
        }
        videoView = null
        chatModel = null
    }
}