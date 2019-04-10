package com.memo.iframe.widget.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * title:可以设置是否滑动的ViewPager
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:50
 */
class ScrollViewPager : ViewPager {

    /**
     * 是否可以滑动
     *
     * @return true 可以 false 不可以
     */
    var isCanScrollable = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isCanScrollable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return try {
            isCanScrollable && super.onInterceptTouchEvent(ev)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}