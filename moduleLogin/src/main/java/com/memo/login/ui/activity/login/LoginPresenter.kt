package com.memo.login.ui.activity.login

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute
import com.memo.iframe.config.constant.Constant
import com.memo.iframe.tools.ext.put
import com.memo.iframe.tools.ext.sp

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 16:57
 */
class LoginPresenter : BasePresenter<LoginModel, LoginContract.View>(), LoginContract.Presenter {


    /**
     * 绑定Model
     */
    override fun buildModel(): LoginModel = LoginModel()

    /**
     * 登陆
     *
     * @param account 账号
     * @param pwd 密码
     */
    override fun signIn(account: String, pwd: String) {
        mModel.signIn(account, pwd)
            .doOnNext {
                sp().put(Constant.SharedPreferences.USERNAME, it.username)
            }
            .execute(mView, false, false) {
                mView.onSignInSuccess()
            }
    }

    /**
     * 注册
     *
     * @param account 账号
     * @param pwd 密码
     * @param rePwd 重复密码
     */
    override fun signUp(account: String, pwd: String, rePwd: String) {
        mModel.signUp(account, pwd, rePwd)
            .execute(mView, false, false) {
                signIn(account, pwd)
            }
    }

}