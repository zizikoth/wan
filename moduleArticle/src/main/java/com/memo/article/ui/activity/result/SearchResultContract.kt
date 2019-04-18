package com.memo.article.ui.activity.result

import com.memo.article.config.entity.Article
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 23:13
 */
interface SearchResultContract {

    interface Model : IModel {
        /**
         * 获取关键字的文章
         */
        fun searchKeywordArticle(page: Int, keyword: String): Observable<Article>
    }

    interface View : IView {

        /**
         * 获取关键字文章成功
         */
        fun onSearchSuccess(response: Article)

        /**
         * 搜索失败
         */
        fun onFailure()

    }

    interface Presenter {
        /**
         * 获取关键字的文章
         * @param page 页码
         * @param keyword 关键字
         */
        fun searchKeywordArticle(page: Int, keyword: String)
    }
}