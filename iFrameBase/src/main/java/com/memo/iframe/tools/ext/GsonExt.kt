package com.memo.iframe.tools.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * title:Gson的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 15:48
 */
/**
 * 实体类转化为json
 */
fun Any.parse2Json(): String = Gson().toJson(this)

/**
 * json转化为Bean
 */
inline fun <reified T> String.parse2Bean(): T = Gson().fromJson<T>(this, object : TypeToken<T>() {}.type)

/**
 * json转为List
 */
inline fun <reified T> String.parse2Array(Clazz: Class<Array<T>>): ArrayList<T> =
    ArrayList(Gson().fromJson(this, Clazz).asList())


