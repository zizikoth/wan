package com.memo.article.config.api

import com.memo.article.config.entity.*
import com.memo.iframe.config.api.ApiClient
import com.memo.iframe.config.entity.BaseResponse
import com.memo.iframe.config.entity.EmptyResponse
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
val mArticleApi: ArticleApiService by lazy { ApiClient.create(ArticleApiService::class.java) }

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
     * 收藏文章
     */
    @POST("lg/collect/{id}/json")
    fun addFavoriteArticle(@Path("id") id: String): Observable<BaseResponse<EmptyResponse>>

    /**
     * 取消收藏文章
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun removeFavoriteArticle(@Path("id") id: String): Observable<BaseResponse<EmptyResponse>>

    /*
     * 收藏页面取消收藏文章
     */
    @POST("lg/uncollect/{id}/json")
    fun removeFavorite(@Path("id") id: String, @Query("originId") originId: String): Observable<BaseResponse<EmptyResponse>>

    /**
     * 获取体系树
     */
    @GET("tree/json")
    fun getNaviTree(): Observable<BaseResponse<ArrayList<Tree>>>

    /**
     * 获取体系下的文章
     */
    @GET("article/list/{page}/json")
    fun getNaviArticles(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): Observable<BaseResponse<Article>>

    /**
     * 获取项目体系
     */
    @GET("project/tree/json")
    fun getProjectTree(): Observable<BaseResponse<ArrayList<Project>>>

    /**
     * 获取项目下的文章
     */
    @GET("project/list/{page}/json")
    fun getProjectArticles(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): Observable<BaseResponse<Article>>

    /**
     * 获取我的收藏
     * @param page 页码
     */
    @GET("lg/collect/list/{page}/json")
    fun getCollection(@Path("page") page: Int): Observable<BaseResponse<Article>>

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