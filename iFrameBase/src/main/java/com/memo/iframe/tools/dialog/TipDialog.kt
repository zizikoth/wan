package com.memo.iframe.tools.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import com.memo.iframe.R
import com.memo.iframe.tools.ext.onClick
import kotlinx.android.synthetic.main.dialog_tip.view.*
import razerdp.basepopup.BasePopupWindow

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 15:28
 */
class TipDialog constructor(
    context: Context,
    title: String = "提示",
    message: String,
    negative: String = "取消",
    positive: String = "确定"
) : BasePopupWindow(context) {

    /*** 标题 ***/
    private var mTitle: String = title

    /*** 消息 ***/
    private var mMessage: String = message

    /*** 取消 ***/
    private var mNegative: String = negative

    /*** 确定 ***/
    private var mPositive: String = positive

    /*** 监听 ***/
    private var mListener: OnTipClickListener? = null

    /**
     * 初始化
     */
    init {
        popupGravity = Gravity.CENTER
        isAllowDismissWhenTouchOutside = false
        initialize()
    }

    /**
     * 返回一个contentView以作为PopupWindow的contentView
     * **强烈建议使用[BasePopupWindow.createPopupById]，该方法支持读取View的xml布局参数，否则可能会出现与布局不一样的展示从而必须手动传入宽高等参数**
     */
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.dialog_tip)
    }

    /**
     * 显示动画
     */
    override fun onCreateShowAnimation(): Animation {
        return getDefaultAlphaAnimation(true)
    }

    /**
     * 消失动画
     */
    override fun onCreateDismissAnimation(): Animation {
        return getDefaultAlphaAnimation(false)
    }

    /**
     * 初始化
     */
    private fun initialize() {
        contentView.mTvTitle.text = mTitle
        contentView.mTvMessage.text = mMessage
        contentView.mTvNegative.text = mNegative
        contentView.mTvPositive.text = mPositive
        contentView.mTvPositive.onClick {
            mListener?.onPositive()
            dismiss()
        }
        contentView.mTvNegative.onClick {
            mListener?.onNegative()
            dismiss()
        }
    }

    /**
     * 设置点击事件
     */
    fun setOnTipClickListener(onPositive: () -> Unit, onNegative: () -> Unit): TipDialog {
        mListener = object : OnTipClickListener() {
            /**
             * 点击确定
             */
            override fun onPositive() {
                onPositive()
            }

            /**
             * 点击取消
             */
            override fun onNegative() {
                onNegative()
            }
        }
        return this
    }

    /**
     * 设置点击事件
     */
    fun setOnTipClickListener(onPositive: () -> Unit): TipDialog {
        mListener = object : OnTipClickListener() {
            /**
             * 点击确定
             */
            override fun onPositive() {
                onPositive()
            }
        }
        return this
    }

    /**
     * 简化显示方法
     */
    fun show() {
        showPopupWindow()
    }
}