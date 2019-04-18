package com.memo.article.ui.activity.result

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 23:15
 */
class SearchResultPresenter : BasePresenter<SearchResultModel, SearchResultContract.View>(),
    SearchResultContract.Presenter {


    /**
     * 绑定Model
     */
    override fun buildModel(): SearchResultModel = SearchResultModel()

    /**
     * 获取关键字的文章
     * @param page 页码
     * @param keyword 关键字
     */
    override fun searchKeywordArticle(page: Int, keyword: String) {
        mModel.searchKeywordArticle(page, keyword)
            .execute(mView, isFirstLoad, isFirstLoad, {
                isFirstLoad = false
                mView.onSearchSuccess(it)
            }, {
                mView.onFailure()
            })
    }

}