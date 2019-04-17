package com.memo.iframe.tools.net.interceptor

import com.blankj.utilcode.util.LogUtils
import com.memo.iframe.config.constant.Constant
import okhttp3.Interceptor
import okhttp3.Response

/**
 * title:在请求头中添加cookie
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 11:54
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().toString()
        val builder = request.newBuilder()

        val logBuilder: StringBuilder = StringBuilder()

        //通过网址判断是否需要添加Cookie
        val needAddCookie =
            url.contains(Constant.Http.URL_COOKIE_ADD_COLLECTIONS) ||
                    url.contains(Constant.Http.URL_COOKIE_ADD_UNCOLLECTIONS) ||
                    url.contains(Constant.Http.URL_COOKIE_ADD_TODO)
        logBuilder.append("url = $url  \nNeedAddCookie = $needAddCookie")

        if (needAddCookie && Constant.cookie.isNotEmpty()) {
            builder.addHeader(Constant.Http.Cookie, Constant.cookie)
            logBuilder.append("\ncookie = ${Constant.cookie}")
        }
        LogUtils.iTag("Http", logBuilder)
        return chain.proceed(builder.build())
    }
}