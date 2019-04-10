package com.memo.iframe.config.controller

/**
 * title:App全局配置 控制器 不想写在build.gradle中 改一次编译一次 很烦
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 16:44
 */
object AppController {
    /**
     * 是否开启日志打印
     */
    const val isOpenLog: Boolean = true

    /**
     * 是否开启崩溃展示界面
     */
    const val isShowCrash:Boolean = true

    //注意 如果不是测试 不是预发布 那么就是线上发布版本
    /**
     * 是否是测试版本
     */
    const val isDebug: Boolean = true

    /**
     * 是否是预发布版本
     */
    const val isPreRelease: Boolean = false

}