package com.memo.iframe.widget.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.blankj.utilcode.util.LogUtils

/**
 * title:检测滑动快慢的RecyclerView
 * tip:
 *
 * @author zhou
 * @date 2018-08-15 下午2:35
 */
class ScrollRecyclerView : RecyclerView {

    private var listener: ScrollListener? = null
    /**
     * 这个数值在手动滑动浏览界面不会超过
     * 只有在快速滑动的时候才会超过
     * 当然我喜欢整数的原因
     */
    private val limit = 220

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onScrolled(dx: Int, dy: Int) {
        if (dy > limit) {
            LogUtils.iTag("scroll", dy)
        }
        if (listener != null) {
            //这里可以自行进行修改
            if (Math.abs(dy) < limit) {
                listener!!.onScrollSlow()
            } else {
                //就以纵向滑动为例子  到底部了么 到顶部了么
                if (!canScrollVertically(1) || !canScrollVertically(-1)) {
                    listener!!.onScrollSlow()
                } else {
                    listener!!.onScrollFast()
                }
            }
        }
        super.onScrolled(dx, dy)
    }

    fun setOnScrollListener(listener: ScrollListener) {
        this.listener = listener
    }

    interface ScrollListener {
        /**
         * 慢速滑动
         */
        fun onScrollSlow()

        /**
         * 快速滑动
         */
        fun onScrollFast()
    }
}
