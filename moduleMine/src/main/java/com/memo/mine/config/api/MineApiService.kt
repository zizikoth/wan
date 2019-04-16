package com.memo.mine.config.api

import com.memo.iframe.config.api.ApiClient
import com.memo.iframe.config.entity.BaseResponse
import com.memo.iframe.config.entity.EmptyResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-14 02:14
 */

val MineApi: MineApiService by lazy { ApiClient.create(MineApiService::class.java) }

interface MineApiService {

    /**
     * 退出登陆
     */
    @GET("user/logout/json")
    fun loginOut(): Observable<BaseResponse<EmptyResponse>>


}