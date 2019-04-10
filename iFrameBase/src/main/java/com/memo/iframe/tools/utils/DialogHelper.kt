package com.memo.iframe.tools.utils

import android.support.v7.app.AlertDialog
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.PermissionUtils.OnRationaleListener.ShouldRequest
import com.memo.iframe.R

/**
 * title:弹窗提示
 * describe:
 *
 * @author zhou
 * @date 2019-01-30 18:23
 */
object DialogHelper {

    @JvmStatic
    fun showRationaleDialog(shouldRequest: ShouldRequest) {
        val topActivity = ActivityUtils.getTopActivity()
        if (topActivity == null || topActivity.isFinishing) {
            return
        }
        AlertDialog.Builder(topActivity)
            .setTitle(R.string.permission_title)
            .setMessage(R.string.permission_rationale_message)
            .setPositiveButton(R.string.permission_positive) { _, _ -> shouldRequest.again(true) }
            .setNegativeButton(R.string.permission_negative) { _, _ -> shouldRequest.again(false) }
            .setCancelable(false)
            .create()
            .show()
    }

    @JvmStatic
    fun showOpenAppSettingDialog(callback: Callback?) {
        val topActivity = ActivityUtils.getTopActivity()
        if (topActivity == null || topActivity.isFinishing) {
            return
        }
        AlertDialog.Builder(topActivity)
            .setTitle(R.string.permission_title)
            .setMessage(R.string.permission_denied_forever_message)
            .setPositiveButton(R.string.permission_positive) { _, _ ->
                PermissionUtils.launchAppDetailsSettings()
                callback?.onPositive()
            }
            .setNegativeButton(R.string.permission_negative) { _, _ -> callback?.onNegative() }
            .setCancelable(false)
            .create()
            .show()
    }

    @JvmStatic
    fun showNeedPermissionDialog(callback: Callback) {
        val topActivity = ActivityUtils.getTopActivity()
        if (topActivity == null || topActivity.isFinishing) {
            return
        }
        AlertDialog.Builder(topActivity)
            .setTitle(R.string.permission_title)
            .setMessage(R.string.permission_rationale_message)
            .setPositiveButton(R.string.permission_positive) { _, _ -> callback.onPositive() }
            .setNegativeButton(R.string.permission_negative) { _, _ -> callback.onNegative() }
            .setCancelable(false)
            .create()
            .show()
    }


    interface Callback {
        fun onPositive()
        fun onNegative()
    }

}