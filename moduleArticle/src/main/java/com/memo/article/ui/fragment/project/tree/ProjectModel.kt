package com.memo.article.ui.fragment.project.tree

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.Project
import com.memo.iframe.config.api.convert
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 16:55
 */
class ProjectModel : ProjectContract.Model {
    /**
     * 获取项目体系
     */
    override fun getProjectTree(): Observable<ArrayList<Project>> =
        mArticleApi.getProjectTree().convert()
}