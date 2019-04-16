package com.memo.iframe.config.constant

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-20 11:41
 */
class Constant {

    companion object {
        /*** Cookie ***/
        var cookie: String = ""
    }


    /*** 网络请求常量 ***/
    object Http {
        const val Cookie: String = "Cookie"
        const val Set_Cookie: String = "Set-Cookie"

        const val URL_COOKIE_SAVE_LOGIN = "user/login"

        const val URL_COOKIE_ADD_COLLECTIONS = "lg/collect"
        const val URL_COOKIE_ADD_UNCOLLECTIONS = "lg/uncollect"
        const val URL_COOKIE_ADD_ARTICLE = "article"
        const val URL_COOKIE_ADD_TODO = "lg/todo"
    }

    /*** sp存储常量 ***/
    object SharedPreferences {
        const val COOKIE: String = "COOKIE"
        const val USERNAME: String = "USERNAME"
    }
}