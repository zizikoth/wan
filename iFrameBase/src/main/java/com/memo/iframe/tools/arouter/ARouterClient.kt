package com.memo.iframe.tools.arouter

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-10 17:13
 */
object ARouterClient {
    /**
     * 通用模块-通用网页
     */
    @JvmStatic
    fun startAgentWeb(title: String, url: String) {
        ARouter.getInstance().build(ARouterPath.Common.AgentWebActivity)
            .withString("title", title)
            .withString("url", url)
            .navigation()
    }

}