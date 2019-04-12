package com.memo.home.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.home.R
import com.memo.home.ui.fragment.home.HomeFragment

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
        supportFragmentManager.beginTransaction()
            .add(R.id.mContainer, HomeFragment())
            .commitAllowingStateLoss()
    }
}
