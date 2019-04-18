package com.memo.article.ui.activity.search

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.HotKeyword
import com.memo.iframe.config.api.convert
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 22:16
 */
class SearchModel : SearchContract.Model {
    /**
     * 获取热词
     */
    override fun getHotKeyword(): Observable<ArrayList<HotKeyword>> =
        mArticleApi.getHotKeyword().convert()

}