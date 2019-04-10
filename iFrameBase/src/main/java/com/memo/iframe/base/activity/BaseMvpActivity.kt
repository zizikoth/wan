package com.memo.iframe.base.activity

import com.memo.iframe.base.mvp.IPresenter
import com.memo.iframe.base.mvp.IView

/**
 * title:基础的Mvp模式Activity
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:08
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity() {

    protected lateinit var mPresenter: P

    override fun initBaseMvp() {
        mPresenter = buildPresenter()
        mPresenter.attachView(this as V)
    }

    /**
     * 绑定Presenter
     */
    protected abstract fun buildPresenter(): P

}