package com.memo.wan.module.ui.splash

import android.content.Intent
import com.blankj.utilcode.util.BarUtils
import com.memo.iframe.base.activity.BaseActivity
import com.memo.iframe.config.constant.Constant
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.ext.SP
import com.memo.iframe.tools.handler.WeakHandler

/**
 * title:启动页
 * describe:
 *
 * @author zhou
 * @date 2019-04-04 17:20
 */
class SplashActivity : BaseActivity() {

    private val WHAT_LOGIN = 1
    private val WHAT_MAIN = 2

    private var mHandler: WeakHandler = WeakHandler {
        when (it.what) {
            WHAT_LOGIN -> ARouterClient.startLogin()
            WHAT_MAIN -> ARouterClient.startMain()
        }
        finish()
        true
    }

    override fun bindLayoutResId(): Int = -1

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {}

    /**
     * 进行初始化控件
     */
    override fun initView() {
        BarUtils.setNavBarVisibility(mActivity, false)
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {}

    /**
     * 开始进行业务操作
     */
    override fun start() {
        val cookie = SP().getString(Constant.SharedPreferences.COOKIE, "")
        if (cookie.isNullOrEmpty()) {
            mHandler.sendEmptyMessageDelayed(WHAT_LOGIN, 1000)
        } else {
            Constant.cookie = cookie
            mHandler.sendEmptyMessageDelayed(WHAT_MAIN, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.onDestroy()
    }
}
