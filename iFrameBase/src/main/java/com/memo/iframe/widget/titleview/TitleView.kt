package com.memo.iframe.widget.titleview

import android.app.Activity
import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.memo.iframe.R
import com.memo.iframe.tools.ext.inflaterView
import com.memo.iframe.tools.ext.onClick
import kotlinx.android.synthetic.main.title_view.view.*

/**
 * title:标题控件
 * describe:
 *
 * @author zhou
 * @date 2019-04-04 15:02
 */
class TitleView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs, 0) {


    init {
        inflaterView(R.layout.title_view, this)
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView)
        val title: String = typeArray.getString(R.styleable.TitleView_tv_title) ?: "标题"
        typeArray.recycle()
        mTvTitle.text = title
        mIvBack.onClick {
            if (context is Activity) {
                context.finish()
            }
        }
    }

    /**
     * 返回 返回图标
     */
    fun getBackIcon(): ImageView = mIvBack

    /**
     * 获取标题控件
     */
    fun getTitleView(): View = mTvTitle

    /**
     * 设置标题
     */
    fun setTitle(title: CharSequence?) {
        title?.let {
            mTvTitle.text = it
        }
    }

    /**
     * 设置右侧图标
     */
    fun setMoreIcon(@DrawableRes drawableRes: Int) {
        mIvMore.setImageResource(drawableRes)
    }

    /**
     * 设置右侧点击事件
     */
    fun setMoreListener(method: () -> Unit) {
        mIvMore.onClick { method() }
    }
}