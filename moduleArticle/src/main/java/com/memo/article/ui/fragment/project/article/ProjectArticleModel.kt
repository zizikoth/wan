package com.memo.article.ui.fragment.project.article

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.Article
import com.memo.iframe.config.api.convert
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 17:14
 */
class ProjectArticleModel :
    ProjectArticleContract.Model {
    /**
     * 获取项目条目文章
     */
    override fun getProjectArticles(cid: String, page: Int): Observable<Article> =
        mArticleApi.getProjectArticles(page, cid).convert()
}