package com.memo.iframe.tools.utils

import android.view.Gravity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.memo.iframe.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.layout_toast.view.*

/**
 * title:全局通用方法
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 10:24
 */

object CommonHelper {

    //------------------------------- Toast相关 -------------------------------//
    @JvmStatic
    fun toast(message: Any?) {
        message?.let {
            ToastUtils.setGravity(Gravity.CENTER, 0, 0)
            ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
        }
    }

    @JvmStatic
    fun toastNormal(message: Any?) {
        message?.let {
            ToastUtils.setGravity(-1, -1, -1)
            ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
        }
    }

    @JvmStatic
    fun cancelToast() {
        ToastUtils.cancel()
    }

    //------------------------------- Log相关 -------------------------------//
    @JvmStatic
    fun log(vararg messages: Any?) {
        LogUtils.i(messages)
    }

    @JvmStatic
    fun logTag(tag: String, vararg messages: Any?) {
        LogUtils.iTag(tag, messages)
    }

    //------------------------------- 通用 -------------------------------//
    /**
     * 关闭刷新
     */
    @JvmStatic
    fun finishRefresh(refreshLayout: SmartRefreshLayout?) {
        refreshLayout?.let {
            if (it.isRefreshing) {
                it.finishRefresh(400)
            }
            if (it.isLoading) {
                it.finishLoadMore(400)
            }
        }
    }
}