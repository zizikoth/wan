package com.memo.article.ui.fragment.navi


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.memo.article.R
import com.memo.article.config.entity.Tree
import com.memo.article.ui.activity.tree.TreeArticleActivity
import com.memo.article.ui.adapter.NaviAdapter
import com.memo.iframe.base.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_navigation.*

/**
 * title:导航界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 14:59
 */
class NaviFragment : BaseMvpFragment<NaviContract.View, NaviPresenter>(), NaviContract.View {

    /*** 适配器 ***/
    private val mAdapter: NaviAdapter by lazy { NaviAdapter() }

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): NaviPresenter = NaviPresenter()

    /**
     * 绑定布局
     */
    override fun bindLayoutResId(): Int = R.layout.fragment_navigation

    /**
     * 进行初始化数据
     */
    override fun initData(bundle: Bundle?) {}

    /**
     * 进行初始化控件
     */
    override fun initView() {
        mRvList.run {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mAdapter
        }
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        //条目点击
        mAdapter.addOnCustomItemClickListener { _, parentPosition, childPosition ->
            val item = mAdapter.data[parentPosition].children[childPosition]
            TreeArticleActivity.start(mActivity, item.id.toString(), item.name)
        }
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.getNaviTree()
    }

    /**
     * 获取数据成功
     */
    override fun onGetNaviTreeSuccess(response: ArrayList<Tree>) {
        mAdapter.setNewData(response)
    }


}
