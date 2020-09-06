package com.tanshul.chat.viewModel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanshul.chat.helper.ChatFrom
import com.tanshul.chat.helper.ChatType
import com.tanshul.chat.helper.ChatValues.AddChatDelay
import com.tanshul.chat.model.ChatModel
import com.tanshul.chat.ui.R

class ChatScreenViewModel : ViewModel() {
    private var itemsAdded = 0
    private val chats = ArrayList<ChatModel>()
    val clearText = MutableLiveData<Boolean>()
    val chatText = MutableLiveData<String>().apply { postValue("") }
    val chatList: MutableLiveData<List<ChatModel>> by lazy { MutableLiveData() }

    fun addRoutineChats() {
        Handler().postDelayed({
            addChatItem()
        }, AddChatDelay)
    }

    fun onChatTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        chatText.postValue(s.toString())
    }

    fun onChatSend() {
        addItem(ChatModel(ChatType.TEXT, ChatFrom.ME, chatText.value))
        clearText.postValue(true)
    }

    private fun addChatItem() {
        val resourceId: Int
        val type = when (itemsAdded++) {
            0 -> {
                resourceId = R.raw.chat_image
                ChatType.IMAGE
            }
            1 -> {
                resourceId = R.raw.chat_music
                ChatType.AUDIO
            }
            2 -> {
                resourceId = R.raw.chat_video
                ChatType.VIDEO
            }
            else -> return
        }
        addItem(ChatModel(type, ChatFrom.OTHER, resourceId = resourceId))
        addRoutineChats()
    }

    private fun addItem(chatModel: ChatModel) {
        chats.add(chatModel)
        chatList.postValue(chats)
    }
}