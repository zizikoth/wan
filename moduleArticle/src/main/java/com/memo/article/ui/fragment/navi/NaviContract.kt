package com.memo.article.ui.fragment.navi

import com.memo.article.config.entity.Tree
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:00
 */
interface NaviContract {
    interface Model : IModel {
        /**
         * 获取知识树
         */
        fun getNaviTree(): Observable<ArrayList<Tree>>
    }

    interface View : IView {
        /**
         * 获取数据成功
         */
        fun onGetNaviTreeSuccess(response: ArrayList<Tree>)
    }

    interface Presenter {
        /**
         * 获取知识树
         */
        fun getNaviTree()
    }
}