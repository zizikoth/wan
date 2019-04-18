package com.memo.article.ui.activity.favorite

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:24
 */
class FavoritePresenter : BasePresenter<FavoriteModel, FavoriteContract.View>(),
    FavoriteContract.Presenter {

    /**
     * 绑定Model
     */
    override fun buildModel(): FavoriteModel =
        FavoriteModel()

    /**
     * 获取我的收藏
     */
    override fun getFavoriteList(page: Int) {
        mModel.getFavoriteList(page)
            .execute(mView, isFirstLoad, isFirstLoad, {
                isFirstLoad = false
                mView.onGetFavoriteSuccess(it)
            }, {
                mView.onGetFavoriteFailure()
            })
    }


    /**
     * 删除我的收藏
     */
    override fun removeFavorite(id: Int, originId: Int) {
        mModel.removeFavorite(id, originId)
            .execute(mView, false, true) {
                mView.onRemoveFavoriteSuccess(id)
            }
    }
}