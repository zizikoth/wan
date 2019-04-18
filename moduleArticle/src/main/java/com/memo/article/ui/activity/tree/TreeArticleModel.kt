package com.memo.article.ui.activity.tree

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.Article
import com.memo.iframe.config.api.convert
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:56
 */
class TreeArticleModel : TreeArticleContract.Model {
    /**
     * 获取体系下的文章
     */
    override fun getTreeArticles(cid: String, page: Int): Observable<Article> =
        mArticleApi.getNaviArticles(page, cid).convert()
}