package com.memo.article.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.article.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start()
    }

    private fun start() {
        //        LoginApi.signIn(AppController.account, AppController.pwd)
        //            .convert().execute {
        //                supportFragmentManager.beginTransaction()
        //                    .add(R.id.mContainer, ArticleFragment())
        //                    .commitAllowingStateLoss()
        //            }
    }
}
