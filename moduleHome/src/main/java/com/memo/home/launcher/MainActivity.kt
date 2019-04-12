package com.memo.home.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.memo.home.R
import com.memo.home.ui.fragment.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.mContainer, HomeFragment())
            .commitAllowingStateLoss()
    }
}
