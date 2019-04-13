package com.memo.article.ui.activity.search

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 22:16
 */
class SearchPresenter : BasePresenter<SearchModel, SearchContract.View>(),
    SearchContract.Presenter {

    /**
     * 绑定Model
     */
    override fun buildModel(): SearchModel = SearchModel()

    /**
     * 获取热词
     */
    override fun getHotKeyword() {
        mModel.getHotKeyword()
            .execute(mView, false, false) {
                mView.onHotKeySuccess(it)
            }
    }
}