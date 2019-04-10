package com.memo.iframe.tools.preview

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.memo.iframe.tools.glide.GlideApp
import com.previewlibrary.loader.IZoomMediaLoader
import com.previewlibrary.loader.MySimpleTarget

/**
 * title:大图预览加载器
 * describe:
 *
 * @author zhou
 * @date 2019-01-31 17:13
 */
class PreviewImageLoader : IZoomMediaLoader {


    override fun displayImage(context: Fragment, path: String, imageView: ImageView, simpleTarget: MySimpleTarget) {
        GlideApp.with(context)
            .asBitmap()
            .load(path)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Bitmap>,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onLoadFailed(null)
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    model: Any,
                    target: Target<Bitmap>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onResourceReady()
                    return false
                }
            })
            .into(imageView)
    }

    override fun displayGifImage(context: Fragment, path: String, imageView: ImageView, simpleTarget: MySimpleTarget) {
        GlideApp.with(context)
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(path)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<GifDrawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onLoadFailed(null)
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onResourceReady()
                    return false
                }
            })
            .into(imageView)
    }

    override fun onStop(context: Fragment) {
        Glide.with(context).onStop()

    }

    override fun clearMemory(c: Context) {
        Glide.get(c).clearMemory()
    }
}
