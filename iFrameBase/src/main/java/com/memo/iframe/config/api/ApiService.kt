package com.memo.wan.config.api

import com.memo.iframe.config.entity.MainBanner
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import com.memo.iframe.config.entity.BaseResponse
import com.memo.iframe.config.entity.MainArticle
import com.memo.iframe.config.entity.Project

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-25 17:15
 */
interface ApiService {

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

    /**
     * 获取项目列表
     */
    @GET("project/tree/json")
    fun getProject():Observable<BaseResponse<Project>>


}