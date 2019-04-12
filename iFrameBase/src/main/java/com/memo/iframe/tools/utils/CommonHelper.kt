package com.memo.iframe.tools.utils

import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * title:全局通用方法
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 10:24
 */

object CommonHelper {



    //------------------------------- 通用 -------------------------------//
    /**
     * 关闭刷新
     */
    @JvmStatic
    fun finishRefresh(refreshLayout: SmartRefreshLayout?) {
        refreshLayout?.let {
            if (it.isRefreshing) {
                it.finishRefresh(400)
            }
            if (it.isLoading) {
                it.finishLoadMore(400)
            }
        }
    }
}