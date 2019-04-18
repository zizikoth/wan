package com.memo.article.ui.fragment.article

import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.Article
import com.memo.article.config.entity.ArticleData
import com.memo.article.config.entity.MainBanner
import com.memo.article.config.entity.MainData
import com.memo.iframe.config.api.convert
import io.reactivex.Observable
import io.reactivex.functions.Function3

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-27 15:53
 */
class ArticleModel : ArticleContract.Model {

    /**
     * 获取主页文章
     * @param page 页码
     */
    override fun getMainArticle(page: Int): Observable<Article> =
        mArticleApi.getMainArticles(page).convert()


    /**
     * 获取主页文章 和 轮播图
     * @param page 页码
     */
    override fun getMainData(page: Int): Observable<MainData> {
        val topArticle = mArticleApi.getTopArticles().convert()
        val mainArticle = mArticleApi.getMainArticles(page).convert()
        val banners = mArticleApi.getMainBanner().convert()

        return Observable.zip(
            topArticle, mainArticle, banners,
            Function3<ArrayList<ArticleData>, Article, ArrayList<MainBanner>, MainData> { top, article, banner ->
                top.forEach {
                    it.isTop = true
                }
                article.datas.addAll(0, top)
                MainData(article, banner)
            })
    }


}