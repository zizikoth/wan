package com.memo.mine.ui.activity.about

import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import com.memo.iframe.base.activity.BaseActivity
import com.memo.iframe.tools.ext.string
import com.memo.mine.R
import kotlinx.android.synthetic.main.activity_about.*

/**
 * title:关于我们
 * describe:
 *
 * @author zhou
 * @date 2019-04-14 02:19
 */
@Suppress("DEPRECATION")
class AboutActivity : BaseActivity() {


    override fun showStatusView(): Boolean = false

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_about

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {}

    /**
     * 进行初始化控件
     */
    override fun initView() {
        title = "关于我们"
        mTvContent.run {
            text = Html.fromHtml(string(R.string.IntroduceWanAndroid))
            movementMethod = LinkMovementMethod.getInstance()
        }
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {}

    /**
     * 开始进行业务操作
     */
    override fun start() {}

}
