package com.tanshul.chat.helper.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.tanshul.chat.helper.logException

object AppUtils {

    fun getTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    fun getColorRes(context: Context, colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }

    fun getDimension(context: Context, dimenId: Int): Float {
        return context.resources.getDimension(dimenId)
    }

    fun showKeyboard(context: Context?, view: View, isShow: Boolean) {
        try {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (isShow) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                view.requestFocus()
            } else {
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.logException()
        }
    }

    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        getDeviceDisplay(context).getMetrics(displayMetrics)
        return displayMetrics
    }

    private fun getDeviceDisplay(context: Context): Display {
        return (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    }
}