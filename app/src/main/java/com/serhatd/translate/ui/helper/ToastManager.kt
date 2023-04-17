package com.serhatd.translate.ui.helper

import android.content.Context
import android.widget.Toast
import com.serhatd.translate.R

class ToastManager(private val context: Context) {
    fun showToast(code: StringCode, message: String = "") {
        if (message.isEmpty()) {
            val msg = when(code) {
                StringCode.SOMETHING_WENT_WRONG -> context.resources.getString(R.string.msg_something_went_wrong)
                StringCode.SRC_TEXT_EMPTY -> context.resources.getString(R.string.msg_src_text_empty)
                StringCode.SELECT_LANGUAGE -> context.resources.getString(R.string.msg_select_language)
                StringCode.SAME_LANGUAGE -> context.resources.getString(R.string.msg_same_language)
            }

            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}