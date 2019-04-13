package com.memo.collect.ui.collect

import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:我的收藏
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:24
 */
interface CollectContract {

    interface Model : IModel {
        /**
         * 获取我的收藏
         */
        fun getCollection(page: Int): Observable<Any>
    }

    interface View : IView {
        /**
         * 获取收藏成功
         */
        fun onCollectionSuccess()
    }

    interface Presenter {
        /**
         * 获取我的收藏
         */
        fun getCollection(page: Int)
    }
}