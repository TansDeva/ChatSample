package com.tanshul.chat.helper.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tanshul.chat.helper.show

class EmptyRecyclerView(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int
) : RecyclerView(context, attrs, defStyle) {
    private var emptyView: View? = null

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    init {
        adapter?.registerAdapterDataObserver(object: AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                checkIfEmpty()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                checkIfEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkIfEmpty()
            }
        })
    }

    private fun checkIfEmpty() {
        val itemCount = adapter?.itemCount ?: 0
        emptyView?.let {
            it.show(itemCount == 0)
            show(itemCount != 0)
        }
    }

    fun setEmptyView(view: View) {
        emptyView = view
        checkIfEmpty()
    }
}