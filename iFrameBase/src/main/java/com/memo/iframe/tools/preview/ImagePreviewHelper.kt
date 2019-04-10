package com.memo.iframe.tools.preview

import android.app.Activity
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.BarUtils
import com.previewlibrary.GPreviewBuilder
import java.util.*

/**
 * title: 大图预览帮助类
 * tip:
 *
 * @author zhou
 * @date 2018-09-17 下午1:34
 */
object ImagePreviewHelper {

    fun start(mActivity: Activity, url: String, view: View) {
        val preview = PreviewImageInfo(url)
        val bounds = Rect()
        view.getGlobalVisibleRect(bounds)
        val statusBarHeight = BarUtils.getStatusBarHeight()
        bounds.top += statusBarHeight
        bounds.bottom += statusBarHeight
        preview.bounds = bounds
        GPreviewBuilder
            .from(mActivity)
            .to(ImagePreviewActivity::class.java)
            .setSingleData(preview)
            .setFullscreen(false)
            .setCurrentIndex(0)
            .setSingleFling(true)
            .setSingleShowType(false)
            .setDrag(false, 0.35f)
            .setType(GPreviewBuilder.IndicatorType.Dot)
            .start()
    }

    /**
     * 开启大图预览
     *
     * @param mActivity          上下文
     * @param mGridLayoutManager 网格布局管理
     * @param imgResId           布局中的图片id
     * @param mData              数据源
     * @param position           点击下标
     */
    fun start(
        mActivity: Activity,
        mGridLayoutManager: GridLayoutManager,
        imgResId: Int,
        mData: List<String>,
        position: Int
    ) {
        val list = ArrayList<PreviewImageInfo>()
        for (url in mData) {
            list.add(PreviewImageInfo(url))
        }
        val firstCompletelyVisiblePos = mGridLayoutManager.findFirstVisibleItemPosition()
        for (i in firstCompletelyVisiblePos until mData.size) {
            val itemView = mGridLayoutManager.findViewByPosition(i)
            val bounds = Rect()
            if (itemView != null) {
                val thumbView = itemView.findViewById<ImageView>(imgResId)
                thumbView.getGlobalVisibleRect(bounds)
            }
            val statusBarHeight = BarUtils.getStatusBarHeight()
            bounds.top += statusBarHeight
            bounds.bottom += statusBarHeight
            list[i].bounds = bounds
        }
        GPreviewBuilder
            .from(mActivity)
            .setData(list)
            .to(ImagePreviewActivity::class.java)
            .setCurrentIndex(position)
            .setFullscreen(false)
            .setSingleFling(true)
            .setSingleShowType(false)
            .setDrag(false, 0.35f)
            .setType(GPreviewBuilder.IndicatorType.Dot)
            .start()
    }
}
