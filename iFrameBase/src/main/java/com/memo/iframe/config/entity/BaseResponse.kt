package com.memo.iframe.config.entity


/**
 * 基础的接口返回数据
 * @param T 实际使用的数据
 * @param errorCode 错误码
 * @param errorMsg 错误信息
 */
class BaseResponse<T>(var data: T?, var errorCode: Int = 0, var errorMsg: String = "")
