package com.memo.login.config.entity

/**
 * title:Login模块数据返回
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 17:56
 */

data class UserInfo(
    var chapterTops: List<Any> = listOf(),
    var collectIds: List<Any> = listOf(),
    var email: String = "",
    var icon: String = "",
    var id: Int = 0, // 21987
    var password: String = "",
    var token: String = "",
    var type: Int = 0, // 0
    var username: String = "" // zhoumemo
)