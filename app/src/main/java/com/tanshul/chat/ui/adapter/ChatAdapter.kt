package com.tanshul.chat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.tanshul.chat.R
import com.tanshul.chat.base.BaseChatHolder
import com.tanshul.chat.databinding.RowChatAudioBinding
import com.tanshul.chat.databinding.RowChatImageBinding
import com.tanshul.chat.databinding.RowChatTextBinding
import com.tanshul.chat.databinding.RowChatVideoBinding
import com.tanshul.chat.helper.AdapterCallback
import com.tanshul.chat.helper.ChatType
import com.tanshul.chat.helper.logException
import com.tanshul.chat.model.ChatModel
import com.tanshul.chat.ui.holder.ChatAudioHolder
import com.tanshul.chat.ui.holder.ChatImageHolder
import com.tanshul.chat.ui.holder.ChatTextHolder
import com.tanshul.chat.ui.holder.ChatVideoHolder

class ChatAdapter(
    private val chatList: MutableList<ChatModel>
) : RecyclerView.Adapter<BaseChatHolder>(), AdapterCallback {
    val itemMap = HashMap<Long, Int>()
    var lastPosition = -1

    fun insertItems(items: List<ChatModel>) {
        val oldCount = itemCount
        var itemsAdded = 0
        items.forEach { item ->
            if (!itemMap.contains(item.timeStamp)) {
                itemMap[item.timeStamp] = itemMap.size
                chatList.add(item)
                itemsAdded++
            }
        }
        if (itemsAdded > 0) {
            notifyItemRangeInserted(oldCount, itemsAdded)
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun getItemViewType(position: Int): Int {
        return chatList[position].type.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseChatHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (viewType) {
            ChatType.IMAGE.ordinal -> ChatImageHolder(
                RowChatImageBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            ChatType.AUDIO.ordinal -> ChatAudioHolder(
                RowChatAudioBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            ChatType.VIDEO.ordinal -> ChatVideoHolder(
                RowChatVideoBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> ChatTextHolder(RowChatTextBinding.inflate(inflater, parent, false))
        }
        when (viewHolder) {
            is ChatAudioHolder -> viewHolder.callback = this
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseChatHolder, position: Int) {
        try {
            if (position !in chatList.indices) return
            with(chatList[position]) {
                holder.bindData(this)
                if (position > lastPosition) {
                    with(holder.itemView) {
                        val animId = if (isMine) R.anim.anim_slide_right else R.anim.anim_slide_left
                        startAnimation(AnimationUtils.loadAnimation(context, animId))
                    }
                    lastPosition = position
                }
            }
        } catch (e: Exception) {
            e.logException()
        }
    }

    override fun onSubmit(itemId: Long, isPlaying: Boolean) {
        itemMap[itemId]?.run {
            chatList[this].isPlaying = isPlaying
            notifyItemChanged(this)
        }
    }
}