package com.memo.iframe.tools.utils

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.memo.iframe.tools.glide.GlideApp

/**
 * title:
 * tip:
 *
 * @author zhou
 * @date 2018-11-13 下午8:37
 */
object GlideImageHelper {

    /**
     * 简单加载中间截取的图片
     */
    @JvmStatic
    fun loadImage(mContext: Context, url: Any, image: ImageView) {
        GlideApp.with(mContext)
            .load(url)
            .centerCrop()
            .into(image)
    }

    /**
     * 简单加载中间截取的图片
     */
    @JvmStatic
    fun loadImage(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        image: ImageView
    ) {
        GlideApp.with(mContext)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .centerCrop()
            .into(image)
    }

    /**
     * 加载中间截取无缓存的图片
     */
    @JvmStatic
    fun loadImageNoCache(mContext: Context, url: Any, image: ImageView) {
        GlideApp.with(mContext)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .centerCrop()
            .into(image)
    }

    /**
     * 加载中间截取无缓存的图片
     */
    @JvmStatic
    fun loadImageNoCache(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        image: ImageView
    ) {
        GlideApp.with(mContext)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(holderRes)
            .error(errorRes)
            .centerCrop()
            .into(image)
    }

    /**
     * 加载圆形图片
     */
    @JvmStatic
    fun loadCircleImage(mContext: Context, url: Any, image: ImageView) {
        GlideApp.with(mContext)
            .load(url)
            .transforms(CenterCrop(), CircleCrop())
            .into(image)
    }


    /**
     * 加载圆形图片
     */
    @JvmStatic
    fun loadCircleImage(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        image: ImageView
    ) {
        GlideApp.with(mContext)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .transforms(CenterCrop(), CircleCrop())
            .into(image)
    }

    /**
     * 加载圆角图片
     */
    @JvmStatic
    fun loadRoundImage(mContext: Context, url: Any, radius: Int, image: ImageView) {
        GlideApp.with(mContext)
            .load(url)
            .transforms(CenterCrop(), RoundedCorners(radius))
            .into(image)
    }

    /**
     * 加载圆角图片
     */
    @JvmStatic
    fun loadRoundImage(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        radius: Int, image: ImageView
    ) {
        GlideApp.with(mContext)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .transforms(CenterCrop(), RoundedCorners(radius))
            .into(image)
    }

    /**
     * 加载重载图片
     */
    @JvmStatic
    fun loadResizeImage(mContext: Context, url: Any, width: Int, height: Int, image: ImageView) {
        GlideApp.with(mContext)
            .load(url)
            .override(width, height)
            .centerCrop()
            .into(image)
    }

    /**
     * 加载重载图片
     */
    @JvmStatic
    fun loadResizeImage(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        width: Int, height: Int, image: ImageView
    ) {
        GlideApp.with(mContext)
            .load(url)
            .override(width, height)
            .centerCrop()
            .placeholder(holderRes)
            .error(errorRes)
            .into(image)
    }

    /**
     * 加载重载大小的圆形图片
     */
    @JvmStatic
    fun loadResizeCircleImage(mContext: Context, url: Any, size: Int, image: ImageView) {
        GlideApp.with(mContext)
            .load(url)
            .override(size)
            .transforms(CenterCrop(), CircleCrop())
            .into(image)
    }

    /**
     * 加载重载大小的圆形图片
     */
    @JvmStatic
    fun loadResizeCircleImage(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        size: Int, image: ImageView
    ) {
        GlideApp.with(mContext)
            .load(url)
            .override(size)
            .placeholder(holderRes)
            .error(errorRes)
            .transforms(CenterCrop(), CircleCrop())
            .into(image)
    }

    /**
     * 加载图片监听
     */
    @JvmStatic
    fun loadImageListener(mContext: Context, url: Any, image: ImageView, listener: RequestListener<Bitmap>) {
        GlideApp.with(mContext)
            .asBitmap()
            .load(url)
            .centerCrop()
            .listener(listener)
            .into(image)
    }

    /**
     * 加载图片监听
     */
    @JvmStatic
    fun loadImageListener(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        image: ImageView, listener: RequestListener<Bitmap>
    ) {
        GlideApp.with(mContext)
            .asBitmap()
            .load(url)
            .centerCrop()
            .placeholder(holderRes)
            .error(errorRes)
            .listener(listener)
            .into(image)
    }

    /**
     * 加载重载圆形图片监听
     */
    @JvmStatic
    fun loadResizeCircleImageListener(
        mContext: Context, url: Any,
        size: Int, image: ImageView, listener: RequestListener<Bitmap>
    ) {
        GlideApp.with(mContext)
            .asBitmap()
            .load(url)
            .override(size)
            .transforms(CenterCrop(), CircleCrop())
            .listener(listener)
            .into(image)
    }

    /**
     * 加载重载圆形图片监听
     */
    @JvmStatic
    fun loadResizeCircleImageListener(
        mContext: Context, url: Any,
        @DrawableRes holderRes: Int, @DrawableRes errorRes: Int,
        size: Int, image: ImageView, listener: RequestListener<Bitmap>
    ) {
        GlideApp.with(mContext)
            .asBitmap()
            .load(url)
            .override(size)
            .placeholder(holderRes)
            .error(errorRes)
            .transforms(CenterCrop(), CircleCrop())
            .listener(listener)
            .into(image)
    }


    /**
     * 暂停加载
     */
    @JvmStatic
    fun onPause(mContext: Context) {
        GlideApp.with(mContext).pauseRequests()
    }

    /**
     * 恢复加载
     */
    @JvmStatic
    fun onResume(mContext: Context) {
        GlideApp.with(mContext).resumeRequests()
    }
}
