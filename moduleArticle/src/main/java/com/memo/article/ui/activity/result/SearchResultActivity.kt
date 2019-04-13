package com.memo.article.ui.activity.result

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.memo.article.R
import com.memo.article.config.entity.Article
import com.memo.article.config.entity.ArticleData
import com.memo.article.ui.adapter.ArticleAdapter
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.ext.startActivity
import com.memo.iframe.tools.utils.CommonHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_search_result.*

/**
 * title:搜索结果界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 23:19
 */
class SearchResultActivity : BaseMvpActivity<SearchResultContract.View, SearchResultPresenter>(),
    SearchResultContract.View {

    companion object {
        fun start(context: Context, keyword: String) {
            context.startActivity<SearchResultActivity>("keyword" to keyword)
        }
    }

    /*** 文章适配器 ***/
    private val mAdapter: ArticleAdapter by lazy { ArticleAdapter() }

    /*** 从上一个页面传递过来的关键字 ***/
    private var keyword: String = ""

    /*** 页码 ***/
    private var page: Int = 0

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): SearchResultPresenter = SearchResultPresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_search_result

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
        keyword = intent.getStringExtra("keyword")
    }

    /**
     * 进行初始化控件
     */
    override fun initView() {
        title = keyword
        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        //列表的点击事件
        mAdapter.setOnItemClickListener { _, _, position ->
            val articleData: ArticleData = mAdapter.data[position]
            ARouterClient.startAgentWeb(articleData.title, articleData.link)
        }

        //刷新
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout?) {
                page = 0
                mPresenter.searchKeywordArticle(page, keyword)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout?) {
                page++
                mPresenter.searchKeywordArticle(page, keyword)
            }
        })
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.searchKeywordArticle(page, keyword)
    }

    /**
     * 获取关键字文章成功
     */
    override fun onSearchSuccess(response: Article) {
        CommonHelper.finishRefresh(mRefreshLayout)
        mRefreshLayout.isEnableLoadMore = page < response.pageCount
        if (page == 0) {
            mAdapter.setNewData(response.datas)
        } else {
            mAdapter.addData(response.datas)
        }
    }

    /**
     * 搜索失败
     */
    override fun onFailure() {
        CommonHelper.finishRefresh(mRefreshLayout)
        page--
    }

}
