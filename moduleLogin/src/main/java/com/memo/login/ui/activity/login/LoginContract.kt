package com.memo.login.ui.activity.login

import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import com.memo.iframe.config.entity.EmptyResponse
import com.memo.login.config.entity.UserInfo
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 16:55
 */
interface LoginContract {

    interface Model : IModel {
        /**
         * 登陆
         */
        fun signIn(account: String, pwd: String): Observable<UserInfo>

        /**
         * 注册
         */
        fun signUp(account: String, pwd: String, rePwd: String): Observable<EmptyResponse>
    }

    interface View : IView {
        /**
         * 登陆成功
         */
        fun onSignInSuccess()

        /**
         * 登陆流程失败了
         */
        fun onFailure()
    }

    interface Presenter {
        /**
         * 登陆
         *
         * @param account 账号
         * @param pwd 密码
         */
        fun signIn(account: String, pwd: String)

        /**
         * 注册
         *
         * @param account 账号
         * @param pwd 密码
         * @param rePwd 重复密码
         */
        fun signUp(account: String, pwd: String, rePwd: String)
    }
}