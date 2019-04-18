package com.memo.article.ui.fragment.article

import com.memo.article.config.entity.MainData
import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-27 14:50
 */
class ArticlePresenter : BasePresenter<ArticleModel, ArticleContract.View>(),
    ArticleContract.Presenter {

    /**
     * 绑定Model
     */
    override fun buildModel(): ArticleModel = ArticleModel()

    /**
     * 获取主页文章
     * @param page 页码
     */
    override fun getMainData(page: Int) {
        mModel.getMainData(page).execute(mView, isFirstLoad, isFirstLoad, {
            isFirstLoad = false
            mView.onSuccessMainData(it)
        }, { mView.onFailureData() })
    }

    /**
     * 获取主页文章
     * @param page 页码
     */
    override fun getMainArticle(page: Int) {
        mModel.getMainArticle(page).execute(mView, false, false, {
            mView.onSuccessMainData(MainData(it))
        }, { mView.onFailureData() })
    }


}