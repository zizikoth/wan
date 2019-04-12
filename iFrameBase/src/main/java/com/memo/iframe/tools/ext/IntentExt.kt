package com.memo.iframe.tools.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import java.io.Serializable

/**
 * title:页面跳转
 * describe:
 *
 * @author zhou
 * @date 2019-03-22 11:20
 */

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
    internalStartActivity(this, T::class.java, params)

inline fun <reified T : Activity> Activity.startActivityForResult(vararg params: Pair<String, Any?>, requestCode: Int) =
    internalStartActivityForResult(this, T::class.java, params, requestCode)

inline fun <reified T : Activity> Context.startActivityAndFinish(vararg params: Pair<String, Any?>) {
    internalStartActivity(this, T::class.java, params)
    if (this is Activity) {
        this.finish()
    }
}

inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) =
    internalStartActivity(activity!!, T::class.java, params)

inline fun <reified T : Activity> Fragment.startActivityForResult(vararg params: Pair<String, Any?>, requestCode: Int) =
    internalStartActivityForResult(this, T::class.java, params, requestCode)

inline fun <reified T : Activity> Fragment.startActivityAndFinish(vararg params: Pair<String, Any?>) {
    internalStartActivity(activity!!, T::class.java, params)
    activity!!.finish()
}


fun internalStartActivity(context: Context, activity: Class<out Activity>, params: Array<out Pair<String, Any?>>) =
    context.startActivity(createIntent(context, activity, params))

fun internalStartActivityForResult(
    activity: Activity,
    clazz: Class<out Activity>,
    params: Array<out Pair<String, Any?>>,
    requestCode: Int
) =
    activity.startActivityForResult(createIntent(activity, clazz, params), requestCode)

fun internalStartActivityForResult(
    fragment: Fragment,
    clazz: Class<out Activity>,
    params: Array<out Pair<String, Any?>>,
    requestCode: Int
) =
    fragment.startActivityForResult(createIntent(fragment.activity!!, clazz, params), requestCode)


private fun <T> createIntent(context: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>): Intent {
    val intent = Intent(context, clazz)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    return intent
}

private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        val value = it.second
        when (value) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
            }
            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
        }
        return@forEach
    }
}