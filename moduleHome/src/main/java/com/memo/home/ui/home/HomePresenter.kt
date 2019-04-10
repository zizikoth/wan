package com.memo.wan.module.home.fragment.home

import com.memo.home.ui.home.HomeContract
import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute
import com.memo.iframe.config.entity.MainData

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-27 14:50
 */
class HomePresenter : BasePresenter<HomeModel, HomeContract.View>(), HomeContract.Presenter {

    /**
     * 绑定Model
     */
    override fun buildModel(): HomeModel = HomeModel()

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
        mModel.getMainArticle(page).execute(mView, {
            mView.onSuccessMainData(MainData(it))
        }, { mView.onFailureData() })
    }


}