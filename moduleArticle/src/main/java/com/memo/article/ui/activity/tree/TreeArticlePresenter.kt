package com.memo.article.ui.activity.tree

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:56
 */
class TreeArticlePresenter : BasePresenter<TreeArticleModel, TreeArticleContract.View>(),
    TreeArticleContract.Presenter {
    /**
     * 绑定Model
     */
    override fun buildModel(): TreeArticleModel = TreeArticleModel()

    /**
     * 获取体系下的文章
     */
    override fun getTreeArticle(cid: String, page: Int) {
        mModel.getTreeArticles(cid, page)
            .execute(mView, isFirstLoad, isFirstLoad, {
                isFirstLoad = false
                mView.onGetArticlesSuccess(it)
            }, {
                mView.onGetArticlesFailure()
            })
    }
}