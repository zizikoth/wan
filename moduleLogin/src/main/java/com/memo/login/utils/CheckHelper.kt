package com.memo.login.utils

import com.memo.iframe.tools.utils.toast

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 16:48
 */
object CheckHelper {

    /**
     * 验证登陆信息
     * @param account 账号
     * @param pwd 密码
     * @return 是否验证通过
     */
    @JvmStatic
    fun checkSignIn(account: String?, pwd: String?): Boolean =
        when {
            account.isNullOrEmpty() -> {
                toast("请输入您的账号")
                false
            }
            pwd.isNullOrEmpty() -> {
                toast("请输入您的密码")
                false
            }
            else -> true
        }

    /**
     * 验证注册信息
     * @param account 账号
     * @param pwd 密码
     * @param rePwd 再次输入的密码
     * @return 是否验证通过
     */
    @JvmStatic
    fun checkSignUp(account: String?, pwd: String?, rePwd: String?): Boolean =
        when {
            account.isNullOrEmpty() -> {
                toast("请输入您的账号")
                false
            }
            pwd.isNullOrEmpty() -> {
                toast("请输入您的密码")
                false
            }
            rePwd.isNullOrEmpty() -> {
                toast("请验证您输入的密码")
                false
            }
            pwd != rePwd -> {
                toast("您输入的密码不一致")
                false
            }
            else -> true
        }


}