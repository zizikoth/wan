package com.memo.Common.web

import android.content.Context
import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.just.agentweb.AgentWeb
import com.memo.Common.R
import com.memo.Common.utils.Helper
import com.memo.iframe.base.activity.BaseActivity
import com.memo.iframe.tools.arouter.ARouterPath
import kotlinx.android.synthetic.main.activity_argent_web.*

/**
 * title:通用网页界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-04 17:09
 */
@Route(path = ARouterPath.Common.AgentWebActivity)
class AgentWebActivity : BaseActivity() {

    /*** 不显示状态控件 ***/
    override fun showStatusView(): Boolean = false

    companion object {
        fun start(context: Context, title: String, url: String) {
            context.startActivity(
                    Intent(context, AgentWebActivity::class.java)
                            .putExtra("title", title)
                            .putExtra("url", url)
            )
        }
    }

    /*** 网页地址 ***/
    private var url: String = ""
    /*** 网页标题 ***/
    private var title: String = ""
    /*** 网页控件 ***/
    private lateinit var mAgentWeb: AgentWeb

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_argent_web

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
        url = intent.getStringExtra("url")
        title = intent.getStringExtra("title")
    }

    /**
     * 初始化控件
     */
    override fun initView() {
        mAgentWeb = Helper.initAgentWeb(mActivity, mFlContainer, url)
        setTitle(title)
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {}

    /**
     * 开始进行业务操作
     */
    override fun start() {}

    /**
     * 点击返回 如果网页可以返回 那么先网页返回
     */
    override fun onBackPressed() {
        if (!mAgentWeb.back()) {
            super.onBackPressed()
        }
    }

    /*** 生命周期策略 ***/
    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

}
