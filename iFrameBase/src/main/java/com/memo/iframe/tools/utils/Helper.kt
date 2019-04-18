package com.memo.iframe.tools.utils

import android.view.Gravity
import com.blankj.utilcode.util.ToastUtils
import com.memo.iframe.R
import kotlinx.android.synthetic.main.layout_toast.view.*

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 15:04
 */
//------------------------------- Toast相关 -------------------------------//
fun toast(message: Any?) {
    message?.let {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
        ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
    }
}

fun toastNormal(message: Any?) {
    message?.let {
        ToastUtils.setGravity(-1, -1, -1)
        ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
    }
}

fun cancelToast() {
    ToastUtils.cancel()
}
