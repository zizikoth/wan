package com.memo.iframe.config.api

import com.google.gson.GsonBuilder
import com.memo.iframe.config.controller.AppController
import com.memo.iframe.tools.net.interceptor.CookieInterceptor
import com.memo.iframe.tools.net.interceptor.HeaderInterceptor
import com.memo.iframe.tools.net.interceptor.HttpLogInterceptor
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 18:09
 */
class ApiClient private constructor() {

    var mRetrofit: Retrofit

    companion object {
        private val INSTANCE: ApiClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiClient()
        }

        fun <T> create(service: Class<T>): T = INSTANCE.mRetrofit.create(service)
    }

    init {

        val mOkHttpClient = RetrofitUrlManager.getInstance()
            .with(OkHttpClient.Builder())
            .addInterceptor(HttpLogInterceptor(AppController.isOpenLog, "Http"))
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(CookieInterceptor())
            .retryOnConnectionFailure(true)
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()
        //Gson配置
        val gson = GsonBuilder()
            //当字段值为空或null时，依然对该字段进行转换
            .serializeNulls()
            //对结果进行格式化，增加换行
            .setPrettyPrinting()
            //防止特殊字符出现乱码
            .disableHtmlEscaping()
            .create()

        mRetrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(mOkHttpClient)
            .build()

    }


}