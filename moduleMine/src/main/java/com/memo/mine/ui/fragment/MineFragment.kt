package com.memo.mine.ui.fragment


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.memo.iframe.base.fragment.BaseFragment
import com.memo.iframe.config.constant.Constant
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.ext.sp
import com.memo.iframe.tools.ext.startActivity
import com.memo.iframe.tools.ext.string
import com.memo.mine.R
import com.memo.mine.config.entity.MineData
import com.memo.mine.ui.activity.about.AboutActivity
import com.memo.mine.ui.activity.setting.SettingActivity
import com.memo.mine.ui.adapter.MineAdapter
import kotlinx.android.synthetic.main.fragment_mine.*


/**
 * title:我的界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-14 02:18
 */
class MineFragment : BaseFragment() {

    private val Favorite: String = "我的收藏"
    private val About: String = "关于我们"

    private val mAdapter: MineAdapter by lazy { MineAdapter() }

    private val mData: ArrayList<MineData> by lazy { arrayListOf<MineData>() }

    override fun showStatusView(): Boolean = false

    /**
     * 绑定布局
     */
    override fun bindLayoutResId(): Int = R.layout.fragment_mine

    /**
     * 进行初始化数据
     */
    override fun initData(bundle: Bundle?) {
        mData.apply {
            add(MineData(R.drawable.ic_mine_favorite, Favorite))
            add(MineData(R.drawable.ic_mine_about, About))
        }
    }

    /**
     * 进行初始化控件
     */
    override fun initView() {
        mTitleView.setTitle("我的")
        mTvName.text =
            sp().getString(Constant.SharedPreferences.USERNAME, string(R.string.UnKnowName))
        mRvList.run {
            layoutManager = LinearLayoutManager(mActivity)
            mAdapter.setNewData(mData)
            adapter = mAdapter
        }
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        // 设置
        mTitleView.setOnMoreListener {
            startActivity<SettingActivity>()
        }
        // 模块点击
        mAdapter.addOnItemChildClickListener { _, position ->
            when (mAdapter.data[position].title) {
                //我的收藏
                Favorite -> {
                    ARouterClient.startFavorite()
                }
                //关于我们
                About -> {
                    startActivity<AboutActivity>()
                }
            }
        }
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
    }


}
