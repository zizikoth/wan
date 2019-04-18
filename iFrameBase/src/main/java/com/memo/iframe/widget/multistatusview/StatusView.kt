package com.memo.iframe.widget.multistatusview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.FrameLayout
import com.memo.iframe.R
import com.memo.iframe.tools.ext.*
import com.memo.iframe.tools.simple.SimpleAnimationListener
import kotlinx.android.synthetic.main.status_view.view.*

/**
 * title:状态控件
 * describe:
 *
 * @author zhou
 * @date 2019-04-04 16:22
 */
class StatusView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val STATUS_HIDE_LOAD = 1
    private val STATUS_HIDE_ALL = 2

    private var hideStatus = STATUS_HIDE_LOAD

    private var mRetryListener: OnRetryClickListener? = null

    private val mAlphaAnim by lazy {
        val alpha = AlphaAnimation(1f, 0f)
        alpha.duration = 300
        alpha
    }

    init {
        inflaterView(R.layout.status_view, this)
        setBackgroundColor(color(R.color.color_F5F5F5))
        mStatusError.onClick { retry() }
        mAlphaAnim.setAnimationListener(object : SimpleAnimationListener() {
            override fun onAnimationEnd(animation: Animation?) {
                when (hideStatus) {
                    STATUS_HIDE_ALL -> hide()
                    STATUS_HIDE_LOAD -> mStatusLoading.gone()
                }
            }
        })
    }

    /**
     * 显示加载框
     */
    fun showLoading(tip: String? = "加载中") {
        mTvStatusLoading.text = tip
        mStatusLoading.visible()
        visible()
    }

    /**
     * 显示数据异常
     */
    fun showDataError() {
        hideStatus = STATUS_HIDE_LOAD
        mStatusLoading.startAnimation(mAlphaAnim)
        mStatusError.visible()
        mTvStatusError.setCompoundDrawablesWithIntrinsicBounds(
            null,
            drawable(R.drawable.status_data_error),
            null,
            null
        )
        mTvStatusError.text = "啊欧，数据不存在"
        visible()
    }

    /**
     * 显示服务器异常
     */
    @SuppressLint("SetTextI18n")
    fun showServerError() {
        hideStatus = STATUS_HIDE_LOAD
        mStatusLoading.startAnimation(mAlphaAnim)
        mStatusError.visible()
        mTvStatusError.setCompoundDrawablesWithIntrinsicBounds(
            null,
            drawable(R.drawable.status_server_error),
            null,
            null
        )
        mTvStatusError.text = "Boom，服务器异常"
        visible()
    }

    /**
     * 显示网络异常
     */
    fun showNetError() {
        hideStatus = STATUS_HIDE_LOAD
        mStatusLoading.startAnimation(mAlphaAnim)
        mStatusError.visible()
        mTvStatusError.setCompoundDrawablesWithIntrinsicBounds(
            null,
            drawable(R.drawable.status_net_error),
            null,
            null
        )
        mTvStatusError.text = "呜呜呜，网路异常"
        visible()
    }

    /**
     * 隐藏全部
     */
    fun hideAll() {
        hideStatus = STATUS_HIDE_ALL
        startAnimation(mAlphaAnim)
    }

    private fun hide() {
        setGone(mStatusLoading, mStatusError)
        setBackgroundColor(Color.TRANSPARENT)
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
