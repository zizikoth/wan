package com.memo.article.ui.fragment.project.article

import com.memo.article.config.entity.Article
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 17:10
 */
interface ProjectArticleContract {
    interface Model : IModel {
        /**
         * 获取项目条目文章
         */
        fun getProjectArticles(cid: String, page: Int): Observable<Article>
    }

    interface View : IView {
        /**
         * 获取项目条目成功
         */
        fun onGetProjectArticleSuccess(response: Article)

        /**
         * 获取失败
         */
        fun onGetProjectArticleFailure()
    }

    interface Presenter {
        /**
         * 获取项目条目文章
         */
        fun getProjectArticles(cid: String, page: Int)
    }
}