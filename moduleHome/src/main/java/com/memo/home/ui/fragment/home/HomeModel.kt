package com.memo.home.ui.fragment.home

import com.memo.home.config.api.HomeApi
import com.memo.home.config.entity.MainArticle
import com.memo.home.config.entity.MainBanner
import com.memo.home.config.entity.MainData
import com.memo.iframe.config.api.convert
import com.memo.iframe.tools.utils.RxSchedulersHelper
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-27 15:53
 */
class HomeModel : HomeContract.Model {

    /**
     * 获取主页文章
     * @param page 页码
     */
    override fun getMainArticle(page: Int): Observable<MainArticle> =
        HomeApi.getMainArticles(page).convert()


    /**
     * 获取主页文章 和 轮播图
     * @param page 页码
     */
    override fun getMainData(page: Int): Observable<MainData> {
        val articles = HomeApi.getMainArticles(page).convert()
        val banners = HomeApi.getMainBanner().convert()
        return Observable.zip(articles, banners,
            BiFunction<MainArticle, List<MainBanner>, MainData> { article, banner ->
                MainData(article, banner)
            }).compose(RxSchedulersHelper.io2Main())
    }


}