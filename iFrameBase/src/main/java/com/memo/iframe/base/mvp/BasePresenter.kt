package com.memo.iframe.base.mvp

/**
 * title:BasePresenter
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:31
 */
abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V> {

    protected lateinit var mView: V

    protected lateinit var mModel: M

    protected var isFirstLoad: Boolean = true

    /**
     * 绑定Model
     */
    abstract fun buildModel(): M

    /**
     * 绑定 View
     */
    override fun attachView(mView: V) {
        this.mView = mView
        mModel = buildModel()
    }

}