package com.memo.article.ui.activity.favorite

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.article.R
import com.memo.article.config.entity.Article
import com.memo.article.config.entity.ArticleData
import com.memo.article.ui.adapter.FavoriteAdapter
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.arouter.ARouterPath
import com.memo.iframe.tools.utils.CommonHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_favorite.*

/**
 * title:我的收藏
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 13:45
 */
@Route(path = ARouterPath.Article.FavoriteActivity)
class FavoriteActivity : BaseMvpActivity<FavoriteContract.View, FavoritePresenter>(),
    FavoriteContract.View {

    private val mAdapter by lazy { FavoriteAdapter() }

    /*** 页码 ***/
    private var page: Int = 0

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): FavoritePresenter = FavoritePresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_favorite

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
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
        //刷新
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout?) {
                page = 0
                mPresenter.getFavoriteList(page)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout?) {
                mPresenter.getFavoriteList(++page)
            }
        })
        mAdapter.addOnItemChildClickListener { view, position ->
            val article: ArticleData = mAdapter.data[position]
            when (view.id) {
                R.id.mTvDelete -> {
                    mPresenter.removeFavorite(article.id, article.originId)
                }
                R.id.mTvContent -> {
                    ARouterClient.startAgentWeb(article.title, article.id.toString())
                }
            }
        }
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.getFavoriteList(0)
    }

    /**
     * 获取收藏成功
     */
    override fun onGetFavoriteSuccess(response: Article) {
        CommonHelper.finishRefresh(mRefreshLayout)
        mRefreshLayout.isEnableLoadMore = page < response.pageCount
        if (page == 0) {
            mAdapter.setNewData(response.datas)
        } else {
            mAdapter.addData(response.datas)
        }
    }

    /**
     * 获取收藏失败
     */
    override fun onGetFavoriteFailure() {
        page = CommonHelper.reducePage(page)
        CommonHelper.finishRefresh(mRefreshLayout)
    }

    /**
     * 删除我的收藏成功
     */
    override fun onRemoveFavoriteSuccess(id: Int) {
        //删除条目
        for ((position, article) in mAdapter.data.withIndex()) {
            if (article.id == id) {
                mAdapter.remove(position)
                break
            }
        }
    }

}
