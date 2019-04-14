package com.memo.iframe.tools.ext

import android.content.Context
import android.content.SharedPreferences
import com.memo.iframe.BuildConfig
import com.memo.iframe.base.application.BaseApplication

/**
 * title:对SharedPreferences的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 17:09
 */
fun Any.sp(name: String = BuildConfig.APPLICATION_ID): SharedPreferences =
    BaseApplication.app.getSharedPreferences(name, Context.MODE_PRIVATE)

/**
 * 批处理
 */
fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    edit().apply { action() }.apply()
}

/**
 * 传入基本类型
 */
fun SharedPreferences.put(key: String, value: Any) {
    edit {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
        }
    }
}

/**
 * 移除key
 */
fun SharedPreferences.remove(key: String) {
    edit {
        remove(key)
    }
}





fun SharedPreferences.clear() {
    edit { clear() }
}