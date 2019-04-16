package com.memo.article.ui.fragment.project.tree

import com.memo.article.config.entity.Project
import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 16:53
 */
interface ProjectContract {
    interface Model : IModel {
        /**
         * 获取项目体系
         */
        fun getProjectTree(): Observable<ArrayList<Project>>
    }

    interface View : IView {
        /**
         * 获取项目体系成功
         */
        fun onGetProjectTreeSuccess(response: ArrayList<Project>)
    }

    interface Presenter {
        /**
         * 获取项目体系
         */
        fun getProjectTree()
    }
}