package com.memo.article.ui.activity.search

import com.memo.article.config.entity.HotKeyword
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 22:10
 */
interface SearchContract {

    interface Model : IModel {
        /**
         * 获取热词
         */
        fun getHotKeyword(): Observable<ArrayList<HotKeyword>>

    }

    interface View : IView {
        /**
         * 获取热词成功
         */
        fun onHotKeySuccess(response: ArrayList<HotKeyword>)

    }

    interface Presenter {
        /**
         * 获取热词
         */
        fun getHotKeyword()
    }
}