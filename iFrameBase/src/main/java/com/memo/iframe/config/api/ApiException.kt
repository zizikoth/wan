package com.memo.iframe.config.api

/**
 * title:一些错误
 * describe:
 *
 * @author zhou
 * @date 2019-02-28 10:06
 */
class ApiException(var code: Int, message: String) : Exception(message)