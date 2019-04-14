package com.memo.mine.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.mine.R

/**
 * title:我的模块启动页
 * describe:
 *
 * @author zhou
 * @date 2019-04-14 21:48
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        LoginApi.signIn(AppController.account, AppController.pwd)
        //            .convert().execute {
        //                supportFragmentManager.beginTransaction()
        //                    .add(R.id.mFlContainer, MineFragment())
        //                    .commitAllowingStateLoss()
        //            }
    }
}
