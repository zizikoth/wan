package com.memo.iframe.tools.arouter

/**
 * title:ARouter路径地址
 * describe:
 *
 * @author zhou
 * @date 2019-04-10 17:07
 */
object ARouterPath {

    /**
     * App模块
     */
    object Main {
        const val MainActivity: String = "/Main/MainActivity"
    }

    /**
     * 通用模块路径
     */
    object Common {
        const val AgentWebActivity: String = "/Common/AgentWebActivity"
    }

    /**
     * 登陆模块
     */
    object Login {
        const val LoginActivity: String = "/Login/LoginActivity"
    }

    /**
     * 主页模块路径
     */
    object Article {
        const val ArticleFragment: String = "/Home/ArticleFragment"
    }

    /**
     * 导航模块路径
     */
    object Navigation

    /**
     * 项目模块路径
     */
    object Project

    /**
     * 我的模块路径
     */
    object Mine

    /**
     * 收藏模块
     */
    object Collect {
        const val CollectActivity: String = "/Collect/CollectActivity"
    }

}