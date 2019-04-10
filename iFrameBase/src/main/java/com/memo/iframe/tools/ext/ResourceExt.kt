package com.memo.iframe.tools.ext

import android.app.Dialog
import android.app.Service
import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * title: 获取资源文件
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 14:43
 */

/**
 * 从Context中获取资源
 */
fun Context.color(id: Int): Int = ContextCompat.getColor(this, id)

fun Context.string(id: Int): String = resources.getString(id)

fun Context.stringArray(id: Int): Array<String> = resources.getStringArray(id)

fun Context.drawable(id: Int) = ContextCompat.getDrawable(this, id)

fun Context.dimen(id: Int) = resources.getDimension(id)

fun Context.dp2px(dp: Float): Int = (dp * resources.displayMetrics.density + 0.5f).toInt()

fun Context.px2dp(px: Float): Int = (px / resources.displayMetrics.density + 0.5f).toInt()

fun Context.sp2px(sp: Float): Int = (sp * resources.displayMetrics.scaledDensity + 0.5f).toInt()

fun Context.px2sp(px: Float): Int = (px / resources.displayMetrics.scaledDensity + 0.5f).toInt()

fun Context.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
        View.inflate(this, layoutRes, parent)


/**
 * 从Fragment中获取资源
 */
fun Fragment.color(id: Int) = context!!.color(id)

fun Fragment.string(id: Int) = context!!.string(id)

fun Fragment.stringArray(id: Int) = context!!.stringArray(id)

fun Fragment.drawable(id: Int) = context!!.drawable(id)

fun Fragment.dimen(id: Int) = context!!.dimen(id)

fun Fragment.dp2px(dp: Float): Int = context!!.dp2px(dp)

fun Fragment.px2dp(px: Float): Int = context!!.px2dp(px)

fun Fragment.sp2px(sp: Float): Int = context!!.sp2px(sp)

fun Fragment.px2sp(px: Float): Int = context!!.px2sp(px)

fun Fragment.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
        View.inflate(context, layoutRes, parent)


/**
 * 从Dialog中获取资源
 */
fun Dialog.color(id: Int) = context.color(id)

fun Dialog.string(id: Int) = context.string(id)

fun Dialog.stringArray(id: Int) = context.stringArray(id)

fun Dialog.drawable(id: Int) = context.drawable(id)

fun Dialog.dimen(id: Int) = context.dimen(id)

fun Dialog.dp2px(dp: Float): Int = context.dp2px(dp)

fun Dialog.px2dp(px: Float): Int = context.px2dp(px)

fun Dialog.sp2px(sp: Float): Int = context.sp2px(sp)

fun Dialog.px2sp(px: Float): Int = context.px2sp(px)

fun Dialog.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
        View.inflate(context, layoutRes, parent)


/**
 * 从service中获取资源
 */
fun Service.color(id: Int) = applicationContext.color(id)

fun Service.string(id: Int) = applicationContext.string(id)

fun Service.stringArray(id: Int) = applicationContext.stringArray(id)

fun Service.drawable(id: Int) = applicationContext.drawable(id)

fun Service.dimen(id: Int) = applicationContext.dimen(id)

fun Service.dp2px(dp: Float): Int = applicationContext.dp2px(dp)

fun Service.px2dp(px: Float): Int = applicationContext.px2dp(px)

fun Service.sp2px(sp: Float): Int = applicationContext.sp2px(sp)

fun Service.px2sp(px: Float): Int = applicationContext.px2sp(px)


/**
 * 从View中获取资源
 */
fun View.color(id: Int) = context.color(id)

fun View.string(id: Int) = context.string(id)

fun View.stringArray(id: Int) = context.stringArray(id)

fun View.drawable(id: Int) = context.drawable(id)

fun View.dimen(id: Int) = context.dimen(id)

fun View.dp2px(dp: Float): Int = context.dp2px(dp)

fun View.px2dp(px: Float): Int = context.px2dp(px)

fun View.sp2px(sp: Float): Int = context.sp2px(sp)

fun View.px2sp(px: Float): Int = context.px2sp(px)

fun View.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
        View.inflate(context, layoutRes, parent)


/**
 * 从RecyclerView中获取资源
 */
fun RecyclerView.ViewHolder.color(id: Int) = itemView.color(id)

fun RecyclerView.ViewHolder.string(id: Int) = itemView.string(id)

fun RecyclerView.ViewHolder.stringArray(id: Int) = itemView.stringArray(id)

fun RecyclerView.ViewHolder.drawable(id: Int) = itemView.drawable(id)

fun RecyclerView.ViewHolder.dimenPx(id: Int) = itemView.dimen(id)

fun RecyclerView.ViewHolder.dp2px(dp: Float): Int = itemView.dp2px(dp)

fun RecyclerView.ViewHolder.px2dp(px: Float): Int = itemView.px2dp(px)

fun RecyclerView.ViewHolder.sp2px(sp: Float): Int = itemView.sp2px(sp)

fun RecyclerView.ViewHolder.px2sp(px: Float): Int = itemView.px2sp(px)

fun RecyclerView.ViewHolder.inflaterView(@LayoutRes layoutRes: Int, parent: ViewGroup? = null): View =
        View.inflate(itemView.context, layoutRes, parent)


