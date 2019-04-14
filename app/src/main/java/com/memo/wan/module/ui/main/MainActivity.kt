package com.memo.wan.module.ui.main

import android.content.Intent
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.article.ui.fragment.article.ArticleFragment
import com.memo.iframe.base.activity.BaseActivity
import com.memo.iframe.tools.arouter.ARouterPath
import com.memo.iframe.tools.ext.gone
import com.memo.iframe.tools.utils.ClickHelper
import com.memo.iframe.tools.utils.FragmentHelper
import com.memo.iframe.tools.utils.cancelToast
import com.memo.mine.ui.fragment.MineFragment
import com.memo.wan.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * title:主页面
 * describe:
 *
 * @author zhou
 * @date 2019-03-11 15:02
 */
@Route(path = ARouterPath.Main.MainActivity)
class MainActivity : BaseActivity() {

    override fun showTitleView(): Boolean = false

    override fun showStatusView(): Boolean = false

    private val mHomeFragment1: Fragment by lazy { ArticleFragment() }
    private val mHomeFragment2: Fragment by lazy { ArticleFragment() }
    private val mHomeFragment3: Fragment by lazy { ArticleFragment() }
    private val mHomeFragment4: Fragment by lazy { MineFragment() }

    private val fragmentHelper: FragmentHelper by lazy {
        FragmentHelper(
            R.id.mFlContainer,
            supportFragmentManager
        )
    }

    override fun bindLayoutResId(): Int = R.layout.activity_main

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {}


    override fun initView() {
        //设置标题
        getTitleView()?.getBackIcon()?.gone()
        getTitleView()?.setTitle("首页")

        //设置底部控件
        mBottomView.run {
            enableAnimation(false)
            enableShiftingMode(false)
            enableItemShiftingMode(false)
        }

        //设置Fragment
        fragmentHelper
            .add(mHomeFragment1, mHomeFragment2, mHomeFragment3, mHomeFragment4)
            .show()
    }

    /**
     * 践行初始化监听
     */
    override fun initListener() {
        //底部导航监听
        mBottomView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.id_main_home -> fragmentHelper.change(0)
                R.id.id_main_project -> fragmentHelper.change(1)
                R.id.id_main_navigation -> fragmentHelper.change(2)
                R.id.id_main_mine -> fragmentHelper.change(3)
            }
            true
        }
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {}

    override fun onBackPressed() {
        //双击退出
        ClickHelper.doubleClickExit()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelToast()
    }

}
