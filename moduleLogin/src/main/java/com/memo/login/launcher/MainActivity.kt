package com.memo.login.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.iframe.tools.ext.startActivityAndFinish
import com.memo.login.R
import com.memo.login.ui.activity.login.LoginActivity

/**
 * title:登陆模块的主界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 16:21
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivityAndFinish<LoginActivity>()
    }
}
