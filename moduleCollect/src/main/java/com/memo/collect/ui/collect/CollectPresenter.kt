package com.memo.collect.ui.collect

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:24
 */
class CollectPresenter : BasePresenter<CollectModel, CollectContract.View>(),
    CollectContract.Presenter {
    /**
     * 绑定Model
     */
    override fun buildModel(): CollectModel = CollectModel()

    /**
     * 获取我的收藏
     */
    override fun getCollection(page: Int) {
        mModel.getCollection(page)
            .execute(mView, isFirstLoad) {

            }
    }
}