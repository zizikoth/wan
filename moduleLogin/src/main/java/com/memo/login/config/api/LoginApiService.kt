package com.memo.login.config.api

import com.memo.iframe.config.api.ApiClient
import com.memo.iframe.config.entity.BaseResponse
import com.memo.login.config.entity.UserInfo
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 17:02
 */

val LoginApi: LoginApiService by lazy { ApiClient.create(LoginApiService::class.java) }

interface LoginApiService {

    /**
     * 注册
     * @param account 账号
     * @param pwd 密码
     * @param rePwd 重复密码
     */
    @POST("user/register")
    fun signUp(
        @Query("username") account: String,
        @Query("password") pwd: String,
        @Query("repassword") rePwd: String
    ): Observable<BaseResponse<Any>>

    /**
     * 登陆
     * @param account 账号
     * @param pwd 密码
     */
    @POST("user/login")
    fun signIn(
        @Query("username") account: String,
        @Query("password") pwd: String
    ): Observable<BaseResponse<UserInfo>>
}