package com.memo.iframe.base.mvp

import io.reactivex.disposables.Disposable

/**
 * title: 基础View接口
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:05
 */
interface IView {

    /**
     * 显示加载框
     */
    fun showLoading(tip: String = "加载中")

    /**
     * 显示网络错误
     */
    fun showNetError()

    /**
     * 数据错误
     */
    fun showDataError()

    /**
     * 服务器错误
     */
    fun showServerError()

    /**
     * 隐藏全部
     */
    fun hideAll()


    /**
     * 将请求放入队列
     */
    fun addDisposable(dispose: Disposable)
}