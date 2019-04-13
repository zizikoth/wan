package com.memo.iframe.config.api

import com.memo.iframe.base.mvp.IView
import com.memo.iframe.config.entity.BaseResponse
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.utils.RxSchedulersHelper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 17:35
 */
/*
 *  扩展数据转换
 */
fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> =
    this.flatMap {
        when (it.errorCode) {
            //请求成功
            ApiErrorCode.SUCCESS -> {
                if (it.data == null) {
                    Observable.error(ApiException(ApiErrorCode.DATA_ERROR, "返回数据为空"))
                } else {
                    Observable.just(it.data)
                }
            }
            //Token失效 重新登陆
            ApiErrorCode.TOKEN_UN_LOGIN -> Observable.error(ApiException(it.errorCode, it.errorMsg))
            //服务器异常
            else -> Observable.error(ApiException(it.errorCode, it.errorMsg))
        }
    }


fun <T> Observable<T>.execute(
    view: IView?,
    isFirstLoad: Boolean,
    isShowLoading: Boolean,
    onSuccess: (T) -> Unit,
    onFailure: () -> Unit
) {
    this.compose(RxSchedulersHelper.io2Main())
        .subscribe(object : Observer<T> {
            override fun onSubscribe(disposable: Disposable) {
                view?.addDisposable(disposable)
                if (isShowLoading) {
                    view?.showLoading()
                }
            }

            override fun onNext(response: T) {
                onSuccess(response)
                view?.hideAll()
            }

            override fun onError(exception: Throwable) {
                when (ApiExceptionParser.parseException(exception)) {
                    ApiErrorCode.SERVER_ERROR -> {
                        if (isFirstLoad) {
                            view?.showServerError()
                        } else {
                            view?.hideAll()
                        }
                    }
                    ApiErrorCode.NETWORK_ERROR -> {
                        if (isFirstLoad) {
                            view?.showNetError()
                        } else {
                            view?.hideAll()
                        }
                    }
                    ApiErrorCode.DATA_ERROR -> {
                        if (isFirstLoad) {
                            view?.showDataError()
                        } else {
                            view?.hideAll()
                        }
                    }
                    ApiErrorCode.TOKEN_UN_LOGIN -> {
                        ARouterClient.startLogin()
                        view?.hideAll()
                    }
                    else -> {
                        //这里是接口返回的errorCode
                        view?.hideAll()
                    }
                }
                onFailure()

            }

            override fun onComplete() {}
        })
}

fun <T> Observable<T>.execute(
    view: IView?,
    isFirstLoad: Boolean,
    onSuccess: (T) -> Unit,
    onFailure: () -> Unit
) {
    execute(view, isFirstLoad, true, onSuccess, onFailure)
}

fun <T> Observable<T>.execute(
    view: IView?,
    isFirstLoad: Boolean,
    onSuccess: (T) -> Unit
) {
    execute(view, isFirstLoad, true, onSuccess, {})
}


fun <T> Observable<T>.execute(
    view: IView?,
    isFirstLoad: Boolean = false,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
) {
    execute(view, isFirstLoad, isShowLoading, onSuccess, {})
}

fun <T> Observable<T>.execute(
    view: IView?,
    onSuccess: (T) -> Unit,
    onFailure: () -> Unit
) {
    execute(view, false, false, onSuccess, onFailure)
}

fun <T> Observable<T>.execute(onSuccess: (T) -> Unit) {
    execute(null, true, true, onSuccess, {})
}



