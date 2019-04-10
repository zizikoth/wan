package com.memo.iframe.tools.utils

import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils

/**
 * title:权限请求工具
 * describe:
 *
 * @author zhou
 * @date 2019-01-30 17:25
 */
object PermissionHelper {

    private const val CAMERA = android.Manifest.permission.CAMERA
    private const val RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO
    private const val WRITE_EXTERNAL_STORAGE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private const val READ_EXTERNAL_STORAGE = android.Manifest.permission.READ_EXTERNAL_STORAGE

    /**
     * 摄像头
     */
    @JvmStatic
    fun requestCamera(): Boolean {
        if (PermissionUtils.isGranted(CAMERA)) {
            return true
        }
        request(PermissionConstants.CAMERA)
        return false
    }


    /**
     * 摄像头和录音
     */
    @JvmStatic
    fun requestCameraAndAudio(): Boolean {
        if (PermissionUtils.isGranted(CAMERA, RECORD_AUDIO)) {
            return true
        }
        request(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE)
        return false
    }

    /**
     * 读取磁盘权限
     */
    @JvmStatic
    fun requestStorageInSplash(callback: PermissionUtils.SimpleCallback) {
        if (PermissionUtils.isGranted(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)) {
            callback.onGranted()
            return
        }
        PermissionUtils.permission(PermissionConstants.STORAGE)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                    callback.onGranted()
                }

                override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {
                    if (!permissionsDeniedForever.isEmpty()) {
                        DialogHelper.showOpenAppSettingDialog(object :
                            DialogHelper.Callback {
                            override fun onPositive() {
                                //这里跳转到应用设置界面了
                                callback.onDenied()
                            }

                            override fun onNegative() {
                                callback.onDenied()
                            }
                        })
                        return
                    } else {
                        DialogHelper.showNeedPermissionDialog(object :
                            DialogHelper.Callback {
                            override fun onPositive() {
                                requestStorageInSplash(callback)
                            }

                            override fun onNegative() {
                                callback.onDenied()
                            }
                        })
                    }
                }
            })
            .request()

    }

    private fun request(@PermissionConstants.Permission vararg permissions: String) {
        PermissionUtils.permission(*permissions)
            .rationale { shouldRequest -> DialogHelper.showRationaleDialog(shouldRequest) }
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {

                }

                override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {
                    if (!permissionsDeniedForever.isEmpty()) {
                        DialogHelper.showOpenAppSettingDialog(null)
                        return
                    } else {
                        DialogHelper.showNeedPermissionDialog(object :
                            DialogHelper.Callback {
                            override fun onPositive() {
                                request(*permissions)
                            }

                            override fun onNegative() {}
                        })
                    }
                }
            })
            .request()
    }
}