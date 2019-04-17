package com.memo.article.ui.activity.favorite

import com.memo.article.config.entity.Article
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import com.memo.iframe.config.entity.EmptyResponse
import io.reactivex.Observable

/**
 * title:我的收藏
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:24
 */
interface FavoriteContract {

    interface Model : IModel {
        /**
         * 获取我的收藏
         */
        fun getFavoriteList(page: Int): Observable<Article>

        /**
         * 删除我的收藏
         */
        fun removeFavorite(id: Int, originId: Int): Observable<EmptyResponse>
    }

    interface View : IView {
        /**
         * 获取收藏成功
         */
        fun onGetFavoriteSuccess(response: Article)

        /**
         * 获取收藏失败
         */
        fun onGetFavoriteFailure()

        /**
         * 删除我的收藏成功
         */
        fun onRemoveFavoriteSuccess(id: Int)
    }

    interface Presenter {
        /**
         * 获取我的收藏
         */
        fun getFavoriteList(page: Int)

        /**
         * 删除我的收藏
         */
        fun removeFavorite(id: Int, originId: Int)
    }
}