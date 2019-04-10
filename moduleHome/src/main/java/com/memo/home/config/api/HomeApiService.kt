package com.memo.home.config.api

import com.memo.home.config.entity.MainArticle
import com.memo.home.config.entity.MainBanner
import com.memo.iframe.config.entity.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * title:Api接口
 * describe:
 *
 * @author zhou
 * @date 2019-02-25 17:15
 */
interface HomeApiService {

    /**
     * 获取主页文章列表
     * @param page 页码
     */
    @GET("article/list/{page}/json")
    fun getMainArticles(@Path("page") page: Int): Observable<BaseResponse<MainArticle>>

    /**
     * 获取主页轮播图
     */
    @GET("banner/json")
    fun getMainBanner(): Observable<BaseResponse<List<MainBanner>>>

}