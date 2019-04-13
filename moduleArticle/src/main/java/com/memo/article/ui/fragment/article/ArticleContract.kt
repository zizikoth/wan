package com.memo.article.ui.fragment.article

import com.memo.article.config.entity.Article
import com.memo.article.config.entity.MainData
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-27 14:49
 */
interface ArticleContract {

    interface Model : IModel {
        /**
         * 获取主页文章 和 轮播图
         * @param page 页码
         */
        fun getMainData(page: Int): Observable<MainData>

        /**
         * 获取主页文章
         * @param page 页码
         */
        fun getMainArticle(page: Int): Observable<Article>
    }

    interface View : IView {
        /**
         * 获取主页文章成功
         * @return 文章数据
         */
        fun onSuccessMainData(response: MainData)

        /**
         * 获取数据失败
         */
        fun onFailureData()
    }

    interface Presenter {
        /**
         * 获取主页文章和轮播图
         * @param page 页码
         */
        fun getMainData(page: Int)

        /**
         * 获取主页文章
         * @param page 页码
         */
        fun getMainArticle(page: Int)
    }
}