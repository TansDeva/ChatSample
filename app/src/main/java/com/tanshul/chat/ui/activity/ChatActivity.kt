package com.tanshul.chat.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tanshul.chat.R
import com.tanshul.chat.base.BaseActivity
import com.tanshul.chat.databinding.ActivityChatBinding
import com.tanshul.chat.helper.ChatValues
import com.tanshul.chat.helper.utils.AppUtils.getColorRes
import com.tanshul.chat.helper.utils.AppUtils.getDisplayMetrics
import com.tanshul.chat.helper.utils.AppUtils.showKeyboard
import com.tanshul.chat.repository.ChatRepository
import com.tanshul.chat.ui.adapter.ChatAdapter
import com.tanshul.chat.viewModel.ChatScreenViewModel
import com.tanshul.chat.viewModel.ViewModelFactory

class ChatActivity : BaseActivity() {
    lateinit var viewModel: ChatScreenViewModel
    lateinit var binding: ActivityChatBinding
    lateinit var chatAdapter: ChatAdapter

    companion object {
        var layoutWidth = 0

        fun newInstance(context: Context) {
            context.startActivity(Intent(context, ChatActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        setupElements()
    }

    private fun setupElements() {
        //Init adapter
        val deviceWidth = getDisplayMetrics(this).widthPixels
        layoutWidth = (ChatValues.ChatWidthRatio * deviceWidth).toInt()
        chatAdapter = ChatAdapter(mutableListOf())
        binding.chatList.adapter = chatAdapter
        binding.chatList.setEmptyView(binding.emptyView)

        //Init ViewModel
        val factory = ViewModelFactory(ChatRepository())
        viewModel = ViewModelProvider(this, factory).get(ChatScreenViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.addRoutineChats()

        //Attach observers
        viewModel.chatText.observe(this, { text ->
            val isValid = text.isNotEmpty()
            val colorId = if (isValid) R.color.chatSendIcon else R.color.chatSendDisable
            binding.sendButton.setColorFilter(getColorRes(this, colorId), PorterDuff.Mode.MULTIPLY)
            binding.sendButton.isEnabled = isValid
        })
        viewModel.chatList.observe(this, { items ->
            chatAdapter.insertItems(items)
        })
        viewModel.clearText.observe(this, { isClear ->
            if (isClear) {
                with(binding.inputText) {
                    showKeyboard(context, this, false)
                    text.clear()
                }
            }
        })
    }

    override fun onDestroy() {
        viewModelStore.clear()
        super.onDestroy()
    }
}