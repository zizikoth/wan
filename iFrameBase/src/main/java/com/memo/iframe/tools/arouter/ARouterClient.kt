package com.memo.iframe.tools.arouter

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
     * Common - 通用网页
     */
    @JvmStatic
    fun startAgentWeb(title: String, url: String) {
        ARouter.getInstance().build(ARouterPath.Common.AgentWebActivity)
            .withString("title", title)
            .withString("url", url)
            .navigation()
    }

    /**
     * app - 主页面
     */
    @JvmStatic
    fun startMain() {
        ARouter.getInstance().build(ARouterPath.Main.MainActivity)
            .navigation()
    }

    /**
     * Login - 登陆界面
     */
    @JvmStatic
    fun startLogin() {
        ARouter.getInstance().build(ARouterPath.Login.LoginActivity)
            .navigation()
    }


    /**
     * Article - 收藏界面
     */
    @JvmStatic
    fun startFavorite() {
        ARouter.getInstance().build(ARouterPath.Article.FavoriteActivity)
            .navigation()
    }


}