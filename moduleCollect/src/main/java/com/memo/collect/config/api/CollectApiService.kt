package com.memo.collect.config.api

import com.memo.iframe.config.api.ApiClient
import com.memo.iframe.config.entity.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:28
 */
val CollectApi: CollectApiService = ApiClient.create(CollectApiService::class.java)

interface CollectApiService {

    /**
     * 获取我的收藏
     * @param page 页码
     */
    @GET("lg/collect/list/{page}/json")
    fun getCollection(@Path("page") page: Int): Observable<BaseResponse<Any>>
}