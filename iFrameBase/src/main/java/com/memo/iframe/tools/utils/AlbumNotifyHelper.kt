package com.memo.iframe.tools.utils

import android.content.Intent
import android.net.Uri
import com.memo.iframe.base.application.BaseApplication

import java.io.File

/**
 * title:相册刷新工具
 * tip:
 *
 * @author zhou
 * @date 2018-09-29 上午10:49
 */
object AlbumNotifyHelper {


    /**
     * 通知相册刷新
     *
     * @param file 文件
     */
    @JvmStatic
    fun refresh(file: File?) {
        if (file != null && file.exists()) {
            BaseApplication.app.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)))
        }
    }

    /**
     * 通知相册刷新
     *
     * @param filePath 文件地址
     */
    @JvmStatic
    fun refresh(filePath: String?) {
        if (filePath != null) {
            refresh(File(filePath))
        }
    }


}
