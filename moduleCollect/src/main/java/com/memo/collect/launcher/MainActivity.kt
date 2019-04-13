package com.memo.collect.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.collect.R
import com.memo.collect.ui.collect.CollectActivity
import com.memo.iframe.config.api.convert
import com.memo.iframe.config.api.execute
import com.memo.iframe.config.controller.AppController
import com.memo.iframe.tools.ext.startActivityAndFinish
import com.memo.login.config.api.LoginApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        //先登陆然后进入收藏
        LoginApi.signIn(AppController.account, AppController.pwd)
            .convert().execute {
                startActivityAndFinish<CollectActivity>()
            }
    }
}
