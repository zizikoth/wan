package com.memo.iframe.tools.banner

import android.content.Context
import android.widget.ImageView
import com.memo.iframe.tools.arouter.ARouterPath
import com.memo.iframe.tools.utils.GlideImageHelper
import com.youth.banner.loader.ImageLoaderInterface

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-03 10:23
 */
class BannerImageLoader : ImageLoaderInterface<ImageView> {
    override fun createImageView(context: Context?): ImageView {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return imageView
    }

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        if (context != null && path != null && imageView != null) {
            GlideImageHelper.loadImage(context, path, imageView)
        }
    }
}