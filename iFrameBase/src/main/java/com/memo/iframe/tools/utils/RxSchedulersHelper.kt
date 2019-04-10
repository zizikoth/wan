package com.memo.iframe.tools.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * title:线程切换
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:10
 */
object RxSchedulersHelper {

    /**
     * 进行子线程和主线程的切换 并且添加到生命周期中
     *
     * @param <T> 返回类型的范型
     * @return ObservableTransformer
    </T> */
    @JvmStatic
    fun <T> io2Main(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 进行计算线程和主线程的切换 并且添加到生命周期中
     *
     * @param <T> 返回类型的范型
     * @return ObservableTransformer
    </T> */
    @JvmStatic
    fun <T> computation2Main(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

