package com.memo.article.utils

import com.memo.iframe.tools.utils.toast

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 22:18
 */
object CheckHelper {

    @JvmStatic
    fun checkSearch(keyword: String?): Boolean {
        return if (keyword.isNullOrEmpty()) {
            toast("想要找些什么？")
            false
        } else {
            true
        }
    }
}