package com.memo.collect.ui.collect

import com.memo.collect.config.api.CollectApi
import com.memo.iframe.config.api.convert
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:25
 */
class CollectModel : CollectContract.Model {
    /**
     * 获取我的收藏
     */
    override fun getCollection(page: Int): Observable<Any> =
        CollectApi.getCollection(page).convert()

}