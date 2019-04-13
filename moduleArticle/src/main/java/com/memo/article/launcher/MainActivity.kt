package com.memo.article.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.article.R
import com.memo.article.ui.fragment.article.ArticleFragment
import com.memo.iframe.config.api.convert
import com.memo.iframe.config.api.execute
import com.memo.iframe.config.controller.AppController
import com.memo.login.config.api.LoginApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        LoginApi.signIn(AppController.account, AppController.pwd)
            .convert().execute {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mContainer, ArticleFragment())
                    .commitAllowingStateLoss()
            }
    }
}
