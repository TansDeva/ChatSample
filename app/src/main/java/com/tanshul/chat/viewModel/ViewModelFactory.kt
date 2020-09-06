package com.tanshul.chat.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanshul.chat.repository.ChatRepository

class ViewModelFactory(
    private val repository: ChatRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatScreenViewModel() as T
    }
}