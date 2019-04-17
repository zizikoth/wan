package com.memo.todo.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.iframe.config.api.convert
import com.memo.iframe.config.api.execute
import com.memo.iframe.config.controller.AppController
import com.memo.iframe.tools.ext.startActivity
import com.memo.login.config.api.mLoginApi
import com.memo.todo.R
import com.memo.todo.ui.activity.todo.TodoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLoginApi.signIn(AppController.account, AppController.pwd)
            .convert()
            .execute {
                startActivity<TodoActivity>()
            }
    }
}
