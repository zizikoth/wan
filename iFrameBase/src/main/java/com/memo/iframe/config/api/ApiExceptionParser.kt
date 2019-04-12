package com.memo.iframe.config.api

import com.blankj.utilcode.util.LogUtils
import com.google.gson.JsonParseException
import com.memo.iframe.tools.utils.toast
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * title:对于异常进行处理
 * describe:
 *
 * @author zhou
 * @date 2019-02-28 17:46
 */
object ApiExceptionParser {
    private const val TAG = "ApiExceptionHandle"

    private var errorCode = ApiErrorCode.SERVER_ERROR
    private var errorMsg = "服务器异常，请稍后重试"

    fun parseException(e: Throwable): Int {
        e.printStackTrace()
        when (e) {
            is SocketTimeoutException,
            is ConnectException,
            is UnknownHostException,
            is HttpException -> {
                LogUtils.eTag(TAG, "网络连接异常: ${e.message}")
                errorMsg = "网络连接异常，请检查网络后尝试"
                errorCode = ApiErrorCode.NETWORK_ERROR
            }
            is JsonParseException,
            is JSONException,
            is ParseException -> {
                LogUtils.eTag(TAG, "数据解析异常: ${e.message}")
                errorMsg = "数据解析异常，请稍后尝试"
                errorCode = ApiErrorCode.SERVER_ERROR
            }
            is IllegalArgumentException -> {
                LogUtils.eTag(TAG, "参数错误: ${e.message}")
                errorMsg = "参数错误"
                errorCode = ApiErrorCode.SERVER_ERROR
            }
            //自定义异常 1、请求的数据为空但是错误码为成功 2、服务器返回的错误
            is ApiException -> {
                LogUtils.eTag(TAG, "服务器请求错误: ${e.message} ErrorCode: ${e.code}")
                errorMsg = "${e.message}"
                errorCode = e.code
            }
            else -> {
                LogUtils.eTag(TAG, "未知错误:${e.javaClass.simpleName}  ${e.message}")
                errorMsg = "啊哦，未知错误"
            }
        }
        toast(errorMsg)
        return errorCode
    }
}