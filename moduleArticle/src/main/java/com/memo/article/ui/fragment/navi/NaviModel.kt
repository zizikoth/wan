package com.memo.article.ui.fragment.navi

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.Tree
import com.memo.iframe.config.api.convert
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:03
 */
class NaviModel : NaviContract.Model {
    /**
     * 获取知识树
     */
    override fun getNaviTree(): Observable<ArrayList<Tree>> =
        mArticleApi.getNaviTree().convert()
}