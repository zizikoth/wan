package com.memo.iframe.tools.utils

import android.content.Context
import android.graphics.Color
import com.memo.iframe.config.constant.Constant
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.dialog.TipDialog
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import java.util.*

/**
 * title:全局通用方法
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 10:24
 */

object CommonHelper {


    //------------------------------- 通用 -------------------------------//
    /**
     * 关闭刷新
     */
    @JvmStatic
    fun finishRefresh(refreshLayout: SmartRefreshLayout?) {
        refreshLayout?.let {
            if (it.isRefreshing) {
                it.finishRefresh(400)
            }
            if (it.isLoading) {
                it.finishLoadMore(400)
            }
        }
    }

    /**
     * 随机创建一个颜色
     */
    @JvmStatic
    fun randomColor(): Int {
        val random = Random()
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        val red = random.nextInt(190)
        val green = random.nextInt(190)
        val blue = random.nextInt(190)
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue)
    }


    @JvmStatic
    fun isSignIn(context: Context): Boolean {
        return if (Constant.cookie.isNotEmpty()) {
            true
        } else {
            TipDialog(context, message = "请登录后操作", positive = "登陆")
                .setOnTipClickListener { ARouterClient.startLogin() }
                .show()
            false
        }
    }
}