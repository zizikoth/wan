package com.memo.iframe.base.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils
import com.memo.iframe.R
import com.memo.iframe.base.mvp.IView
import com.memo.iframe.tools.ext.color
import com.memo.iframe.tools.ext.inflaterView
import com.memo.iframe.tools.ext.setVisibleOrGone
import com.memo.iframe.tools.ext.string
import com.memo.iframe.tools.rxbus.RxBus
import com.memo.iframe.tools.utils.KeyboardHelper
import com.memo.iframe.tools.utils.StatusBarHelper
import com.memo.iframe.widget.multistatusview.StatusView
import com.memo.iframe.widget.titleview.TitleView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_base.view.*

/**
 * title:基础的Activity
 * tip:
 *
 * @author zhou
 * @date 2018-11-14 上午9:54
 */
abstract class BaseActivity : AppCompatActivity(), IView {
    /**
     * TAG
     */
    protected val TAG: String by lazy { javaClass.simpleName }

    /**
     * 上下文
     */
    protected val mContext: Context by lazy { this }

    /**
     * 上下文
     */
    protected val mActivity: BaseActivity by lazy { this }

    /**
     * 内容布局
     */
    protected var mRootView: View? = null

    /**
     * 是否显示标题栏
     */
    protected open fun showTitleView(): Boolean = true

    /**
     * 是否显示状态控件
     */
    protected open fun showStatusView(): Boolean = true

    /**
     * 是否点击空白处隐藏软键盘
     */
    protected open fun clickBlankHideKeyboard(): Boolean = true

    /**
     * 是否去除状态栏
     */
    protected open fun transparentStatusBar(): Boolean = false

    /**
     * 注册Rxbus
     */
    protected open fun registerRxBus(): Boolean = false

    /**
     * 绑定状态栏颜色
     */
    @ColorRes
    protected open fun bindStatusBarColor(): Int = R.color.white

    /**
     * RxJava2请求序列
     */
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }


    override fun onCreate(savedInstanceState: Bundle?) {
        before()
        super.onCreate(savedInstanceState)
        if (bindLayoutResId() != -1) {
            mRootView = inflaterView(R.layout.activity_base)
            inflaterView(bindLayoutResId(), mRootView?.mContentContainer!!)
            setContentView(mRootView)
        }
        initBase()
        initBaseMvp()
        initData(intent)
        initView()
        initListener()
        start()
    }

    /**
     * 在初始化之前进行一些操作
     */
    open fun before() {}

    /**
     * 界面状态
     */
    private fun initBase() {
        if (transparentStatusBar()) {
            StatusBarHelper.setTranslucentForImageView(this, 50, null)
        } else {
            StatusBarHelper.setColor(this, color(bindStatusBarColor()), 50)
        }
        mRootView?.mTitleView?.setVisibleOrGone(showTitleView())
        mRootView?.mActivityStatusView?.setVisibleOrGone(showStatusView())
        mRootView?.mActivityStatusView?.setOnRetryListener { start() }
    }

    /**
     * 绑定布局id
     */
    @LayoutRes
    abstract fun bindLayoutResId(): Int

    /**
     * 在BaseMvpActivity中进行一些操作
     */
    protected open fun initBaseMvp() {}

    /**
     * 进行初始化数据
     */
    protected abstract fun initData(intent: Intent)

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

    /**
     * 分发点击事件 用来隐藏软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (clickBlankHideKeyboard()) {
            KeyboardHelper.clickBlank2HideKeyboard(this, currentFocus, ev)
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 设置标题
     */
    override fun setTitle(title: CharSequence?) {
        mRootView?.mTitleView?.setTitle(title)
    }

    /**
     * 设置标题
     */
    override fun setTitle(titleResId: Int) {
        title = string(titleResId)
    }

    /**
     * 返回标题控件
     */
    fun getTitleView(): TitleView = mRootView!!.mTitleView

    /**
     * 返回状态控件
     */
    fun getStatusView(): StatusView = mRootView!!.mActivityStatusView

    /**
     * 显示加载框
     */
    override fun showLoading(tip: String) {
        if (showStatusView()) {
            mRootView?.mActivityStatusView?.showLoading(tip)
        }
    }

    /**
     * 显示网络错误
     */
    override fun showNetError() {
        if (showStatusView()) {
            mRootView?.mActivityStatusView?.showNetError()
        }
    }

    /**
     * 数据错误
     */
    override fun showDataError() {
        if (showStatusView()) {
            mRootView?.mActivityStatusView?.showDataError()
        }
    }

    /**
     * 服务器错误
     */
    override fun showServerError() {
        if (showStatusView()) {
            mRootView?.mActivityStatusView?.showServerError()
        }
    }

    /**
     * 隐藏全部
     */
    override fun hideAll() {
        if (showStatusView()) {
            mRootView?.mActivityStatusView?.hideAll()
        }
    }

    /**
     * 添加入队列
     */
    override fun addDisposable(dispose: Disposable) {
        compositeDisposable.add(dispose)
    }

    override fun onDestroy() {
        //解除注册RxBus
        if (registerRxBus()) {
            RxBus.get().unregister(this)
        }
        //清空RxJava2序列
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
        //隐藏软键盘
        KeyboardUtils.hideSoftInput(mActivity)
        //修复软键盘内存泄漏
        KeyboardUtils.fixSoftInputLeaks(mActivity)
        super.onDestroy()
    }

}