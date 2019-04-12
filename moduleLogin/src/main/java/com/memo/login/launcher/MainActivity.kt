package com.memo.login.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.iframe.tools.ext.startActivityAndFinish
import com.memo.login.R
import com.memo.login.ui.activity.login.LoginActivity

/**
 * title:单独运行时的主界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-11 00:17
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivityAndFinish<LoginActivity>()
    }
}
