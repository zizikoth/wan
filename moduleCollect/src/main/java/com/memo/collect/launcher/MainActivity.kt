package com.memo.collect.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.collect.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        //先登陆然后进入收藏
        //        LoginApi.signIn(AppController.account, AppController.pwd)
        //            .convert().execute {
        //                startActivityAndFinish<CollectActivity>()
        //            }
    }
}
