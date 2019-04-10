package com.memo.iframe.widget.recyclerview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

/**
 * title:不能滑动的LinearLayoutManager 防止滑动冲突
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:54
 */
class NoScrollLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }
}
