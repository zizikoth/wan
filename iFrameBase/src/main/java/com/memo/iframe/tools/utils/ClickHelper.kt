package com.memo.iframe.tools.utils

import com.blankj.utilcode.util.ActivityUtils

/**
 * title:点击工具类 防止过快点击
 * tip:
 *
 * @author zhou
 * @date 2018/7/24 上午9:35
 */
object ClickHelper {

    private var lastClickTime: Long = 0

    private var exitLastClickTime:Long = 0

    /**
     * 是否过快点击
     *
     * @return true 可以执行  false 不可执行
     */
    @JvmStatic
    val isNotFastClick: Boolean
        get() {
            var flag = false
            val curClickTime = System.currentTimeMillis()
            if (curClickTime - lastClickTime >= 600) {
                flag = true
                lastClickTime = curClickTime
            }
            return flag
        }

    /**
     * 是否过快长按
     *
     * @return true 可以执行  false 不可执行
     */
    @JvmStatic
    val isNotFastLongClick: Boolean
        get() {
            var flag = false
            val curClickTime = System.currentTimeMillis()
            if (curClickTime - lastClickTime >= 1200) {
                flag = true
                lastClickTime = curClickTime
            }
            return flag
        }

    /**
     * 双击退出
     */
    @JvmStatic
    fun doubleClickExit() {
        val now = System.currentTimeMillis()
        if(now - exitLastClickTime >1500){
            toast("再次点击退出应用")
            exitLastClickTime = now
        }else{
            ActivityUtils.finishAllActivities(true)
        }
    }

}
