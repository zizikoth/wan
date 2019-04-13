package com.memo.article.config.api

import com.memo.article.config.entity.Article
import com.memo.article.config.entity.ArticleData
import com.memo.article.config.entity.HotKeyword
import com.memo.article.config.entity.MainBanner
import com.memo.iframe.config.api.ApiClient
import com.memo.iframe.config.entity.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * title:Api接口
 * describe:
 *
 * @author zhou
 * @date 2019-02-25 17:15
 */

/**
 * 创建API
 */
val ArticleApi: ArticleApiService by lazy { ApiClient.create(ArticleApiService::class.java) }

/**
 * API接口
 */
interface ArticleApiService {

    /**
     * 获取置顶文章
     */
    @GET("article/top/json")
    fun getTopArticles(): Observable<BaseResponse<ArrayList<ArticleData>>>

    /**
     * 获取主页文章列表
     * @param page 页码
     */
    @GET("article/list/{page}/json")
    fun getMainArticles(@Path("page") page: Int): Observable<BaseResponse<Article>>

    /**
     * 获取主页轮播图
     */
    @GET("banner/json")
    fun getMainBanner(): Observable<BaseResponse<ArrayList<MainBanner>>>

    /**
     * 获取搜索热词
     */
    @GET("hotkey/json")
    fun getHotKeyword(): Observable<BaseResponse<ArrayList<HotKeyword>>>

    /**
     * 搜索关键词文章
     */
    @POST("article/query/{page}/json")
    fun searchHotKeyword(
        @Path("page") page: Int,
        @Query("k") keyword: String
    ): Observable<BaseResponse<Article>>

}