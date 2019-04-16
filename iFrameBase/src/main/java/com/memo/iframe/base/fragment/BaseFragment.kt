package com.memo.iframe.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils
import com.memo.iframe.R
import com.memo.iframe.base.mvp.IView
import com.memo.iframe.tools.ext.inflaterView
import com.memo.iframe.tools.ext.setVisibleOrGone
import com.memo.iframe.tools.utils.StatusBarHelper
import com.memo.iframe.widget.multistatusview.StatusView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_base.view.*


/**
 * title:基础的Fragment
 * tip:
 *
 * @author zhou
 * @date 2018-11-14 上午10:39
 */
abstract class BaseFragment : Fragment(), IView {

    protected val TAG: String by lazy { javaClass.simpleName }

    protected val mActivity: FragmentActivity by lazy { activity!! }

    private var isPrepare: Boolean = false

    protected val mRootView: View by lazy { inflaterView(R.layout.fragment_base) }

    /**
     * 是否使用懒加载
     */
    protected open fun isUseLazyLoad(): Boolean = true

    /**
     * 是否显示状态控件
     */
    protected open fun showStatusView(): Boolean = true

    /**
     * 是否去除状态栏
     */
    protected open fun transparentStatusBar(): Boolean = false

    /**
     * RxJava2请求序列
     */
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflaterView(bindLayoutResId(), mRootView.mContentContainer!!)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBase()
        initMvp()
        isPrepare = true
        onVisibleToUser()
        if (!isUseLazyLoad()) {
            initialize()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisibleToUser()
        }
    }

    private fun onVisibleToUser() {
        if (isPrepare && userVisibleHint) {
            isPrepare = false
            if (isUseLazyLoad()) {
                lazyInitialize()
            }
        }
    }

    private fun initBase() {
        if (transparentStatusBar()) {
            StatusBarHelper.setTranslucentForImageViewInFragment(mActivity, 50, null)
        }
        mRootView.mFragmentStatusView.setVisibleOrGone(showStatusView())
        if (showStatusView()) {
            mRootView.mFragmentStatusView.setOnRetryListener { start() }
        }
    }

    /**
     * 绑定布局
     */
    protected abstract fun bindLayoutResId(): Int

    /**
     * 对于BaseMvpFragment的初始化
     */
    protected open fun initMvp() {}

    /**
     * 正常加载
     */
    private fun initialize() {
        initData(arguments)
        initView()
        initListener()
        start()
    }

    /**
     * 懒加载
     */
    private fun lazyInitialize() {
        initialize()
    }


    /**
     * 进行初始化数据
     */
    protected abstract fun initData(bundle: Bundle?)

    /**
     * 进行初始化控件
     */
    protected abstract fun initView()

    /**
     * 进行初始化监听
     */
    protected abstract fun initListener()

    /**
     * 开始进行业务操作
     */
    protected abstract fun start()

    fun getStatusView(): StatusView = mRootView.mFragmentStatusView

    /**
     * 显示加载
     */
    override fun showLoading(tip: String) {
        if (showStatusView()) {
            LogUtils.iTag(TAG, "showLoading()")
            mRootView.mFragmentStatusView.showLoading(tip)
        }
    }

    /**
     * 显示网络错误
     */
    override fun showNetError() {
        if (showStatusView()) {
            LogUtils.iTag(TAG, "showNetError()")
            mRootView.mFragmentStatusView.showNetError()
        }
    }

    /**
     * 数据错误
     */
    override fun showDataError() {
        if (showStatusView()) {
            LogUtils.iTag(TAG, "showDataError()")
            mRootView.mFragmentStatusView.showDataError()
        }
    }

    /**
     * 服务器错误
     */
    override fun showServerError() {
        if (showStatusView()) {
            LogUtils.iTag(TAG, "showServerError()")
            mRootView.mFragmentStatusView.showServerError()
        }
    }

    /**
     * 隐藏全部
     */
    override fun hideAll() {
        if (showStatusView()) {
            LogUtils.iTag(TAG, "hideAll()")
            mRootView.mFragmentStatusView.hideAll()
        }
    }

    /**
     * 添加入队列
     */
    override fun addDisposable(dispose: Disposable) {
        compositeDisposable.add(dispose)
    }

    override fun onDestroy() {
        //清空RxJava2序列
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        super.onDestroy()
    }

}