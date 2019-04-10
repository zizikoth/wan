package com.memo.iframe.tools.dialog

/**
 * title:弹窗条目点击
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 14:35
 */
interface OnItemClickListener {
    /**
     * 条目点击
     */
    fun onItemClick(position: Int, item: String)
}


abstract class OnTipClickListener {
    /**
     * 点击确定
     */
    abstract fun onPositive()

    /**
     * 点击取消
     */
    open fun onNegative() {}
}