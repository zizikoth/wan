package com.memo.iframe.base.fragment

import com.memo.iframe.base.mvp.IPresenter
import com.memo.iframe.base.mvp.IView

/**
 * title:基础的MVP模式Fragment
 * describe:
 *
 * @author zhou
 * @date 2019-01-28 13:58
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment() {

    protected lateinit var mPresenter: P

    override fun initMvp() {
        mPresenter = buildPresenter()
        mPresenter.attachView(this as V)
    }

    /**
     * 绑定Presenter
     */
    protected abstract fun buildPresenter(): P

}