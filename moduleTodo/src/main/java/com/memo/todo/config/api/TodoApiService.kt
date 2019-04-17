package com.memo.todo.config.api

import com.memo.iframe.config.api.ApiClient

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-17 14:26
 */

val mTodoApi: TodoApiService by lazy { ApiClient.create(TodoApiService::class.java) }

interface TodoApiService