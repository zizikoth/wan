package com.memo.iframe.base.application

import android.app.Application
import android.content.Context
import android.support.annotation.NonNull
import android.support.multidex.MultiDex
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.memo.crashhunter.CrashHunter
import com.memo.iframe.BuildConfig
import com.memo.iframe.R
import com.memo.iframe.config.controller.AppController
import com.memo.iframe.tools.preview.PreviewImageLoader
import com.previewlibrary.ZoomMediaLoader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import java.text.SimpleDateFormat
import java.util.*


/**
 * title:Application
 * tip:
 *
 * @author zhou
 * @date 2018-11-15 下午6:03
 */
open class BaseApplication : Application() {

    init {
        initRefresh()
    }

    companion object {
        lateinit var app: Application
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        //初始化崩溃界面
        CrashHunter.init(this).isDebug(AppController.isShowCrash)

        //初始化AndroidUtilCode
        Utils.init(this)

        //初始化日志打印
        LogUtils.getConfig()
            .setLogSwitch(AppController.isOpenLog)
            .setLogHeadSwitch(AppController.isOpenLog)
            .setLog2FileSwitch(AppController.isOpenLog)
            .globalTag = "iFrame"

        //初始化内存监听
        //        if (!LeakCanary.isInAnalyzerProcess(this)) {
        //            LeakCanary.install(this)
        //        }

        //大图预览
        ZoomMediaLoader.getInstance().init(PreviewImageLoader())

        if (BuildConfig.DEBUG) {
            // 开启日志
            ARouter.openLog()
            // 使用InstantRun的时候，需要打开该开关，上线之后关闭，否则有安全风险
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }
        ARouter.init(this)

    }

    private fun initRefresh() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            @NonNull
            override fun createRefreshHeader(@NonNull context: Context, @NonNull layout: RefreshLayout): RefreshHeader {
                val mClassicsHeader = ClassicsHeader(context)
                mClassicsHeader.setTimeFormat(SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA))
                mClassicsHeader.spinnerStyle = SpinnerStyle.Scale
                return mClassicsHeader
            }
        })

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            @NonNull
            override fun createRefreshFooter(@NonNull context: Context, @NonNull layout: RefreshLayout): RefreshFooter {
                val footer = BallPulseFooter(context)
                footer.setNormalColor(ContextCompat.getColor(context, R.color.color_E6E6E6))
                footer.setAnimatingColor(ContextCompat.getColor(context, R.color.color_main))
                return footer
            }
        })
    }
}
