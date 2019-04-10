package com.memo.home.ui.fragment.home


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.common.R
import com.memo.home.config.entity.ArticleData
import com.memo.home.config.entity.MainBanner
import com.memo.home.config.entity.MainData
import com.memo.home.ui.adapter.ArticleAdapter
import com.memo.iframe.base.fragment.BaseMvpFragment
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.arouter.ARouterPath
import com.memo.iframe.tools.banner.BannerImageLoader
import com.memo.iframe.tools.ext.inflaterView
import com.memo.iframe.tools.utils.CommonHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.header_layout_home.view.*

/**
 * title:首页
 * describe:
 *
 * @author zhou
 * @date 2019-02-27 16:10
 */
@Route(path = ARouterPath.Home.HomeFragment)
class HomeFragment : BaseMvpFragment<HomeContract.View, HomePresenter>(),
    HomeContract.View {

    /*** 适配器 ***/
    private val mAdapter: ArticleAdapter by lazy { ArticleAdapter() }

    /*** 头布局 ***/
    private val mHeader: View by lazy {
        val inflaterView: View = inflaterView(R.layout.header_layout_home)
        //列表头布局
        inflaterView.mBanner.setImageLoader(BannerImageLoader())
        inflaterView.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        inflaterView
    }

    /*** 页码 ***/
    private var page = 0

    /*** 轮播图数据源 ***/
    private var mBannerData: List<MainBanner>? = null

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): HomePresenter = HomePresenter()

    /**
     * 绑定布局
     */
    override fun bindLayoutResId(): Int = R.layout.fragment_home

    /**
     * 初始化数据
     */
    override fun initData(bundle: Bundle?) {}

    /**
     * 初始化控件
     */
    override fun initView() {
        //列表
        mRvList.run {
            //配置列表
            layoutManager = LinearLayoutManager(activity)
            //添加头布局
            mAdapter.addHeaderView(mHeader)
            adapter = mAdapter
        }
    }

    /**
     * 监听
     */
    override fun initListener() {
        //刷新
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout?) {
                page = 0
                mPresenter.getMainData(page)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout?) {
                mPresenter.getMainArticle(++page)
            }
        })

        //列表的点击事件
        mAdapter.setOnItemClickListener { _, _, position ->
            val articleData: ArticleData = mAdapter.data[position]
            ARouterClient.startAgentWeb(articleData.title, articleData.link)
        }

        //轮播图点击事件
        mHeader.mBanner.setOnBannerListener { position ->
            mBannerData?.let {
                ARouterClient.startAgentWeb(it[position].title, it[position].url)
            }
        }
    }

    /**
     * 开始请求 和 重试
     */
    override fun start() {
        mPresenter.getMainData(page)
    }

    /**
     * 获取主页文章成功
     * @return 文章数据
     */
    override fun onSuccessMainData(response: MainData) {
        //关闭刷新
        CommonHelper.finishRefresh(mRefreshLayout)
        //轮播图
        response.banners?.let {
            mBannerData = it
            val images: ArrayList<String> = arrayListOf()
            val titles: ArrayList<String> = arrayListOf()
            for (entity in it) {
                images.add(entity.imagePath)
                titles.add(entity.title)
            }
            mHeader.mBanner.update(images, titles)
        }
        //文章列表
        response.articles?.let {
            mRefreshLayout.isEnableLoadMore = page <= it.pageCount
            if (page == 0) {
                mAdapter.setNewData(it.datas)
            } else {
                mAdapter.addData(it.datas)
            }
        }
    }

    /**
     * 获取数据失败
     */
    override fun onFailureData() {
        page--
        CommonHelper.finishRefresh(mRefreshLayout)
    }

    override fun onResume() {
        super.onResume()
        mHeader.mBanner.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
        mHeader.mBanner.stopAutoPlay()
    }

}
