package com.memo.iframe.tools.ext

import android.annotation.SuppressLint
import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.TimeUtils
import java.text.SimpleDateFormat
import java.util.*


/**
 * title:时间转换
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 15:18
 */

/**
 *  2018-4-6 11:11:11转为毫秒
 *  @param format 时间的格式，默认是按照yyyy-MM-dd HH:mm:ss来转换
 */
fun String.toMills(format: String = "yyyy-MM-dd HH:mm:ss"): Long =
    SimpleDateFormat(format, Locale.getDefault()).parse(this).time

/**
 * Long类型时间戳转为字符串的日期格式
 * @param format 时间的格式，默认是按照yyyy-MM-dd HH:mm:ss来转换
 */
fun Long.toDate(format: String = "yyyy-MM-dd HH:mm:ss"): String =
    SimpleDateFormat(format, Locale.getDefault()).format(Date(this))

/**
 * 到秒
 */
fun Long.toDateSec(): String = toDate()

/**
 * 到分
 */
fun Long.toDateMin(): String = toDate("yyyy-MM-dd HH:mm")

/**
 * 到日
 */
fun Long.toDateDay(): String = toDate("yyyy-MM-dd")

/**
 * 获取友好时间描述
 * 1554256902000 -> 刚刚
 */
@SuppressLint("SimpleDateFormat")
fun Long.toTimeSpanDescribe(): String {
    val now = System.currentTimeMillis()
    val span = now - this
    return when {
        span < TimeConstants.MIN -> "刚刚"
        span < TimeConstants.HOUR -> String.format(Locale.getDefault(), "%d分钟前", span / TimeConstants.MIN)
        span < TimeConstants.DAY -> String.format(Locale.getDefault(), "%d小时前", span / TimeConstants.HOUR)
        else -> TimeUtils.millis2String(this, SimpleDateFormat("MM - dd"))
    }
}


/**
 * 获取友好时间描述
 * 1990-01-01 10:10:10 -> 1小时前
 */
@SuppressLint("SimpleDateFormat")
fun String.toTimeSpanDescribe(format: String = "yyyy-MM-dd HH:mm:ss"): String {
    val millis = TimeUtils.string2Millis(this, SimpleDateFormat(format))
    return millis.toTimeSpanDescribe()
}

