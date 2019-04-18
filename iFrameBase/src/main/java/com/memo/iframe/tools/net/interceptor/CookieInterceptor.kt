package com.memo.iframe.tools.net.interceptor

import com.blankj.utilcode.util.LogUtils
import com.memo.iframe.config.constant.Constant
import com.memo.iframe.tools.ext.put
import com.memo.iframe.tools.ext.sp
import okhttp3.Interceptor
import okhttp3.Response

/**
 * title:从请求头中获取cookie
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 11:54
 */
class CookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().toString()
        val response = chain.proceed(request)
        val cookies = response.headers(Constant.Http.Set_Cookie)

        val logBuilder: StringBuilder = StringBuilder()

        //通过网址是否需要存储当前cookie 只有登陆才需要存储cookie
        val needSaveCookie = url.contains(Constant.Http.URL_COOKIE_SAVE_LOGIN)

        logBuilder.append("url = $url \nNeedSaveCookie = $needSaveCookie")
        if (needSaveCookie && cookies.isNotEmpty()) {
            //获取cookie
            Constant.cookie = encodeCookie(cookies)
            //存入本地
            sp().put(Constant.SharedPreferences.COOKIE, Constant.cookie)
            logBuilder.append("\ncookies = ${Constant.cookie}")
        }
        LogUtils.iTag("Http", logBuilder)
        return response
    }

    /**
     * 由于cookie数据有很多,放到一个String里面
     */
    private fun encodeCookie(cookies: List<String>): String {
        val builder: StringBuilder = StringBuilder()
        for (cookie in cookies) {
            if (!cookie.endsWith(";")) {
                cookie.plus(";")
            }
            builder.append(cookie)
        }
        return builder.toString()

    }


}