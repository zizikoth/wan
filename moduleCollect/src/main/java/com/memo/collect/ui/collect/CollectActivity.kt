package com.memo.collect.ui.collect

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.collect.R
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.iframe.tools.arouter.ARouterPath

/**
 * title:我的收藏
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:45
 */
@Route(path = ARouterPath.Collect.CollectActivity)
class CollectActivity : BaseMvpActivity<CollectContract.View, CollectPresenter>(),
    CollectContract.View {

    /*** 页码 ***/
    private var page: Int = 0

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): CollectPresenter = CollectPresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_collect

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
    }

    /**
     * 进行初始化控件
     */
    override fun initView() {
        title = "我的收藏"
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.getCollection(0)
    }

    /**
     * 获取收藏成功
     */
    override fun onCollectionSuccess() {
    }

}
