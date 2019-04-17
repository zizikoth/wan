package com.memo.article.ui.fragment.project.article


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.memo.article.R
import com.memo.article.config.entity.Article
import com.memo.article.ui.adapter.ArticleAdapter
import com.memo.iframe.base.fragment.BaseMvpFragment
import com.memo.iframe.tools.utils.CommonHelper
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_project_article.*

/**
 * title:项目文章列表
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 18:07
 */
class ProjectArticleFragment :
    BaseMvpFragment<ProjectArticleContract.View, ProjectArticlePresenter>(),
    ProjectArticleContract.View {

    companion object {
        fun newInstance(cid: String): ProjectArticleFragment {
            val fragment = ProjectArticleFragment()
            val bundle = Bundle()
            bundle.putString("cid", cid)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val mAdapter: ArticleAdapter by lazy { ArticleAdapter() }

    private var cid: String = ""
    private var page: Int = 1

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): ProjectArticlePresenter =
        ProjectArticlePresenter()

    /**
     * 绑定布局
     */
    override fun bindLayoutResId(): Int = R.layout.fragment_project_article

    /**
     * 进行初始化数据
     */
    override fun initData(bundle: Bundle?) {
        bundle?.let {
            cid = it.getString("cid", "")
        }
    }

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
        //刷新
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout?) {
                page = 1
                mPresenter.getProjectArticles(cid, page)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout?) {
                mPresenter.getProjectArticles(cid, ++page)
            }

        })
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.getProjectArticles(cid, page)
    }

    /**
     * 获取项目条目成功
     */
    override fun onGetProjectArticleSuccess(response: Article) {
        CommonHelper.finishRefresh(mRefreshLayout)
        mRefreshLayout.isEnableLoadMore = page < response.pageCount
        if (page == 1) {
            mAdapter.setNewData(response.datas)
        } else {
            mAdapter.addData(response.datas)
        }
    }

    /**
     * 获取失败
     */
    override fun onGetProjectArticleFailure() {
        CommonHelper.finishRefresh(mRefreshLayout)
        page = CommonHelper.reducePage(page)
    }

    /**
     * 滑动到顶部
     */
    fun scrollToTop() {
        CommonHelper.scrollToTop(mRvList)
    }


}
