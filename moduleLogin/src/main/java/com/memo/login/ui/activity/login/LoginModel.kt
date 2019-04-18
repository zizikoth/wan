package com.memo.login.ui.activity.login

import com.memo.iframe.config.api.convert
import com.memo.iframe.config.api.convertEmpty
import com.memo.iframe.config.entity.EmptyResponse
import com.memo.login.config.api.mLoginApi
import com.memo.login.config.entity.UserInfo
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 16:56
 */
class LoginModel : LoginContract.Model {
    /**
     * 登陆
     */
    override fun signIn(account: String, pwd: String): Observable<UserInfo> =
        mLoginApi.signIn(account, pwd).convert()

    /**
     * 注册
     */
    override fun signUp(account: String, pwd: String, rePwd: String): Observable<EmptyResponse> =
        mLoginApi.signUp(account, pwd, rePwd).convertEmpty()

}