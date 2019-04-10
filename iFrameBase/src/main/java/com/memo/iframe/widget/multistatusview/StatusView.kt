package com.memo.iframe.widget.multistatusview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import com.memo.iframe.R
import com.memo.iframe.tools.ext.*
import kotlinx.android.synthetic.main.status_view.view.*

/**
 * title:状态控件
 * describe:
 *
 * @author zhou
 * @date 2019-04-04 16:22
 */
class StatusView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var mRetryListener: OnRetryClickListener? = null

    init {
        inflaterView(R.layout.status_view, this)
        mStatusDataError.onClick { retry() }
        mStatusServerError.onClick { retry() }
        mStatusNetError.onClick { retry() }
    }


    /**
     * 显示加载框
     */
    fun showLoading(tip: String? = "加载中") {
        setBackgroundColor(Color.TRANSPARENT)
        setGone(mStatusDataError, mStatusServerError, mStatusNetError)
        setVisible(mStatusLoading)
        mTvStatusLoading.text = tip
        visible()
    }

    /**
     * 显示数据异常
     */
    fun showDataError() {
        setBackgroundColor(Color.WHITE)
        setGone(mStatusLoading, mStatusServerError, mStatusNetError)
        setVisible(mStatusDataError)
        visible()
    }

    /**
     * 显示服务器异常
     */
    fun showServerError() {
        setBackgroundColor(Color.WHITE)
        setGone(mStatusLoading, mStatusDataError, mStatusNetError)
        setVisible(mStatusServerError)
        visible()
    }

    /**
     * 显示网络异常
     */
    fun showNetError() {
        setBackgroundColor(Color.WHITE)
        setGone(mStatusLoading, mStatusServerError, mStatusDataError)
        setVisible(mStatusNetError)
        visible()
    }

    /**
     * 隐藏全部
     */
    fun hideAll() {
        gone()
    }

    /**
     * 设置重试按钮
     */
    fun setOnRetryListener(method: () -> Unit) {
        mRetryListener = object : OnRetryClickListener {
            override fun onRetry() {
                method()
            }
        }
    }

    /**
     * 重试
     */
    private fun retry() {
        mRetryListener?.onRetry()
    }

    /**
     * 从界面上分离
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeAllViews()
        mRetryListener = null
    }


    /**
     * 重试监听
     */
    interface OnRetryClickListener {
        /**
         * 重试
         */
        fun onRetry()
    }

}
