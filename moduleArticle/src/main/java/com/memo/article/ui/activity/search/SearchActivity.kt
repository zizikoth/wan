package com.memo.article.ui.activity.search

import android.content.Intent
import com.google.android.flexbox.FlexboxLayoutManager
import com.memo.article.R
import com.memo.article.config.entity.HotKeyword
import com.memo.article.ui.activity.result.SearchResultActivity
import com.memo.article.ui.adapter.SearchHotKeywordAdapter
import com.memo.article.utils.CheckHelper
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.iframe.tools.ext.onClick
import com.memo.iframe.tools.ext.setVisibleOrGone
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseMvpActivity<SearchContract.View, SearchPresenter>(),
    SearchContract.View {

    /*** 标签布局 ***/
    private val mLayoutManager: FlexboxLayoutManager by lazy { FlexboxLayoutManager(mContext) }

    /*** 热门搜索适配器 ***/
    private val mAdapter: SearchHotKeywordAdapter by lazy { SearchHotKeywordAdapter() }

    override fun showStatusView(): Boolean = false

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): SearchPresenter = SearchPresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_search

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {}

    /**
     * 进行初始化控件
     */
    override fun initView() {
        title = "发现更多干货"

        mRvList.run {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        //点击搜索
        mIvSearch.onClick {
            val keyword = mEtSearch.text.toString().trim()
            if (CheckHelper.checkSearch(keyword)) {
                SearchResultActivity.start(mContext, keyword)
            }
        }
        //热词搜索
        mAdapter.setOnItemClickListener { _, _, position ->
            SearchResultActivity.start(mContext, mAdapter.data[position].name)
        }
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.getHotKeyword()
    }

    /**
     * 获取热词成功
     */
    override fun onHotKeySuccess(response: ArrayList<HotKeyword>) {
        mLlHotKeyword.setVisibleOrGone(response.isNotEmpty())
        mAdapter.setNewData(response)
    }
}
