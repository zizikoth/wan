package com.memo.article.ui.fragment.navi

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:03
 */
class NaviPresenter : BasePresenter<NaviModel, NaviContract.View>(), NaviContract.Presenter {
    /**
     * 绑定Model
     */
    override fun buildModel(): NaviModel = NaviModel()

    /**
     * 获取知识树
     */
    override fun getNaviTree() {
        mModel.getNaviTree()
            .execute(mView, isFirstLoad, isFirstLoad) {
                isFirstLoad = false
                mView.onGetNaviTreeSuccess(it)
            }
    }
}