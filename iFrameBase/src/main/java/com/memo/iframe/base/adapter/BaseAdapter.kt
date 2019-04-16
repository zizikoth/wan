package com.memo.iframe.base.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.iframe.tools.utils.ClickHelper

/**
 * title:简化BaseQuickAdapter
 * describe:
 *
 * @author zhou
 * @date 2019-04-14 01:45
 */
abstract class BaseAdapter<T, K : BaseViewHolder>(layoutResId: Int, data: List<T>? = null) :
    BaseQuickAdapter<T, K>(layoutResId, data) {

    protected var mOnCustomItemClickListener: OnCustomItemClickListener? = null

    override fun convert(helper: K?, item: T?) {

        helper?.let {
            item?.let {
                initialize(helper, item)
            }
        }
    }

    abstract fun initialize(helper: K, item: T)

    /**
     * 条目点击时间
     */
    fun addOnItemClickListener(click: (view: View, position: Int) -> Unit) {
        setOnItemClickListener { _, view, position ->
            if (ClickHelper.isNotFastClick) {
                click(view, position)
            }
        }
    }

    /**
     * 条目长按点击
     */
    fun addOnItemClickLongClickListener(longClick: (view: View, position: Int) -> Unit) {
        setOnItemLongClickListener { _, view, position ->
            if (ClickHelper.isNotFastLongClick) {
                longClick(view, position)
            }
            false
        }
    }

    /**
     * 设置子控件点击时间
     */
    fun addOnItemChildClickListener(click: (view: View, position: Int) -> Unit) {
        setOnItemChildClickListener { _, view, position ->
            if (ClickHelper.isNotFastClick) {
                click(view, position)
            }
        }
    }

    /**
     * 子控件长按点击
     */
    fun addOnItemChildLongClickListener(longClick: (view: View, position: Int) -> Unit) {
        setOnItemChildLongClickListener { _, view, position ->
            if (ClickHelper.isNotFastLongClick) {
                longClick(view, position)
            }
            false
        }
    }

    /**
     * 设置自定义的条目点击
     */
    fun addOnCustomItemClickListener(click: (view: View, parentPosition: Int, childPosition: Int) -> Unit) {
        this.mOnCustomItemClickListener = object : OnCustomItemClickListener {
            override fun onItemClick(view: View, parentPosition: Int, childPosition: Int) {
                click(view, parentPosition, childPosition)
            }
        }
    }


    /**
     * 自定义条目点击
     */
    interface OnCustomItemClickListener {
        fun onItemClick(view: View, parentPosition: Int, childPosition: Int)
    }
}
