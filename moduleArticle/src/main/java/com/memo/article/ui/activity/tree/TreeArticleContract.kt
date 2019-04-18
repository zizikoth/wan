package com.memo.article.ui.activity.tree

import com.memo.article.config.entity.Article
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:53
 */
interface TreeArticleContract {
    interface Model : IModel {
        /**
         * 获取体系下的文章
         */
        fun getTreeArticles(cid: String, page: Int): Observable<Article>
    }

    interface View : IView {
        /**
         * 获取文章成功
         */
        fun onGetArticlesSuccess(response: Article)

        /**
         * 获取文章失败
         */
        fun onGetArticlesFailure()
    }

    interface Presenter {
        /**
         * 获取体系下的文章
         */
        fun getTreeArticle(cid: String, page: Int)
    }
}