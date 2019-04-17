package com.memo.article.ui.fragment.project.tree


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import com.flyco.tablayout.listener.OnTabSelectListener
import com.memo.article.R
import com.memo.article.config.entity.Project
import com.memo.article.ui.fragment.project.article.ProjectArticleFragment
import com.memo.iframe.base.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * title:项目
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 16:47
 */
@Suppress("DEPRECATION")
class ProjectFragment : BaseMvpFragment<ProjectContract.View, ProjectPresenter>(),
    ProjectContract.View {

    private val fragments: ArrayList<Fragment> by lazy { arrayListOf<Fragment>() }

    override fun showStatusView(): Boolean = false

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): ProjectPresenter = ProjectPresenter()

    /**
     * 绑定布局
     */
    override fun bindLayoutResId(): Int = R.layout.fragment_project

    /**
     * 进行初始化数据
     */
    override fun initData(bundle: Bundle?) {}

    /**
     * 进行初始化控件
     */
    override fun initView() {
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {}

            override fun onTabSelect(position: Int) {
                mVpContainer.currentItem = position
            }
        })
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        mPresenter.getProjectTree()
    }


    /**
     * 获取项目体系成功
     */
    override fun onGetProjectTreeSuccess(response: ArrayList<Project>) {
        val titles: Array<String> = Array(response.size) {
            val article = response[it]
            fragments.add(ProjectArticleFragment.newInstance(article.id.toString()))
            Html.fromHtml(article.name).toString()
        }
        mTabLayout.setViewPager(mVpContainer, titles, mActivity, fragments)
    }

    /**
     * 找到当前Fragment 让列表滑动到顶部
     */
    fun scrollToTop() {
        mTabLayout?.run {
            (fragments[currentTab] as ProjectArticleFragment).scrollToTop()
        }
    }

}
