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
abstract class BaseAdapter<T, K : BaseViewHolder>(layoutResId: Int) :
    BaseQuickAdapter<T, K>(layoutResId) {

    override fun convert(helper: K?, item: T?) {
        helper?.let {
            item?.let {
                initialize(helper, item)
            }
        }
    }

    abstract fun initialize(helper: K, item: T)

    override fun setOnItemClick(v: View?, position: Int) {
        if (ClickHelper.isNotFastClick) {
            super.setOnItemClick(v, position)
        }
    }

    override fun setOnItemLongClick(v: View?, position: Int): Boolean {
        if (ClickHelper.isNotFastLongClick) {
            return super.setOnItemLongClick(v, position)
        }
        return false
    }

}
