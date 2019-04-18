package com.memo.article.ui.activity.tree

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.memo.article.R
import com.memo.article.config.entity.Article
import com.memo.article.ui.adapter.ArticleAdapter
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.iframe.tools.ext.startActivity
import com.memo.iframe.tools.utils.CommonHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_tree_article.*

/**
 * title:体系文章
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:53
 */
class TreeArticleActivity : BaseMvpActivity<TreeArticleContract.View, TreeArticlePresenter>(),
    TreeArticleContract.View {

    companion object {
        fun start(context: Context, cid: String, title: String) {
            context.startActivity<TreeArticleActivity>("cid" to cid, "title" to title)
        }
    }

    private val mAdapter: ArticleAdapter by lazy { ArticleAdapter() }

    private var cid: String = ""
    private var title: String = ""

    private var page: Int = 0

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): TreeArticlePresenter = TreeArticlePresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_tree_article

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
        cid = intent.getStringExtra("cid")
        title = intent.getStringExtra("title")
    }

    /**
     * 进行初始化控件
     */
    override fun initView() {
        setTitle(title)
        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        //刷新
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout?) {
                page = 0
                mPresenter.getTreeArticle(cid, page)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout?) {
                mPresenter.getTreeArticle(cid, ++page)
            }

        })
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.getTreeArticle(cid, page)
    }

    /**
     * 获取文章成功
     */
    override fun onGetArticlesSuccess(response: Article) {
        CommonHelper.finishRefresh(mRefreshLayout)
        mRefreshLayout.isEnableLoadMore = page < response.pageCount
        if (page == 0) {
            mAdapter.setNewData(response.datas)
        } else {
            mAdapter.addData(response.datas)
        }
    }

    /**
     * 获取文章失败
     */
    override fun onGetArticlesFailure() {
        CommonHelper.finishRefresh(mRefreshLayout)
        page = CommonHelper.reducePage(page)
    }

}
