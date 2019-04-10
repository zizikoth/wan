package com.memo.iframe.config.api

/**
 * title:网络请求返回的错误码
 * describe:
 *
 * @author zhou
 * @date 2019-02-28 17:42
 */
object ApiErrorCode {
    /**
     * 响应成功
     */
    const val SUCCESS = 0

    /**
     * Token 过期 未登陆
     */
    const val TOKEN_UN_LOGIN = -1001

    /**
     * 服务器内部错误
     */
    const val SERVER_ERROR = 1002

    /**
     * 网络连接超时
     */
    const val NETWORK_ERROR = 1003

    /**
     * 数据不存在
     */
    const val DATA_ERROR = 1004

}