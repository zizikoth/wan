package com.memo.article.ui.fragment.project.article

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 17:14
 */
class ProjectArticlePresenter : BasePresenter<ProjectArticleModel, ProjectArticleContract.View>(),
    ProjectArticleContract.Presenter {
    /**
     * 绑定Model
     */
    override fun buildModel(): ProjectArticleModel =
        ProjectArticleModel()

    /**
     * 获取项目条目文章
     */
    override fun getProjectArticles(cid: String, page: Int) {
        mModel.getProjectArticles(cid, page)
            .execute(mView, isFirstLoad, isFirstLoad, {
                isFirstLoad = false
                mView.onGetProjectArticleSuccess(it)
            }, {
                mView.onGetProjectArticleFailure()
            })
    }
}