package com.memo.article.ui.activity.result

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.Article
import com.memo.iframe.config.api.convert
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 23:15
 */
class SearchResultModel : SearchResultContract.Model {

    /**
     * 获取关键字的文章
     */
    override fun searchKeywordArticle(page: Int, keyword: String): Observable<Article> =
        mArticleApi.searchHotKeyword(page, keyword).convert()
}