package com.memo.article.ui.activity.favorite

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.Article
import com.memo.iframe.config.api.convert
import com.memo.iframe.config.api.convertEmpty
import com.memo.iframe.config.entity.EmptyResponse
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:25
 */
class FavoriteModel : FavoriteContract.Model {


    /**
     * 获取我的收藏
     */
    override fun getFavoriteList(page: Int): Observable<Article> =
        mArticleApi.getCollection(page).convert()


    /**
     * 删除我的收藏
     */
    override fun removeFavorite(id: Int, originId: Int): Observable<EmptyResponse> =
        mArticleApi.removeFavorite(id.toString(), originId.toString()).convertEmpty()

}