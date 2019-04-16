package com.memo.article.ui.fragment.project.tree

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 16:55
 */
class ProjectPresenter : BasePresenter<ProjectModel, ProjectContract.View>(),
    ProjectContract.Presenter {
    /**
     * 绑定Model
     */
    override fun buildModel(): ProjectModel = ProjectModel()

    /**
     * 获取项目体系
     */
    override fun getProjectTree() {
        mModel.getProjectTree()
            .execute(mView, isFirstLoad, isFirstLoad) {
                isFirstLoad = false
                mView.onGetProjectTreeSuccess(it)
            }
    }
}