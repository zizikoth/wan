package com.memo.mine.ui.activity.setting

import android.content.Intent
import android.net.Uri
import com.blankj.utilcode.util.FileUtils
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.iframe.config.constant.Constant
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.ext.onClick
import com.memo.iframe.tools.ext.remove
import com.memo.iframe.tools.ext.sp
import com.memo.iframe.tools.ext.string
import com.memo.iframe.tools.utils.CommonHelper
import com.memo.mine.R
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * title:设置
 * describe:
 *
 * @author zhou
 * @date 2019-04-14 02:19
 */
class SettingActivity : BaseMvpActivity<SettingContract.View, SettingPresenter>(),
    SettingContract.View {


    override fun showStatusView(): Boolean = false

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): SettingPresenter = SettingPresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_setting

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
    }

    /**
     * 进行初始化控件
     */
    override fun initView() {
        title = "设置"
        mTvCache.text = CommonHelper.convertDirSize(
            mContext.cacheDir,
            mContext.externalCacheDir
        )
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        //删除缓存文件
        mLlCache.onClick {
            FileUtils.deleteDir(mContext.cacheDir)
            FileUtils.deleteDir(mContext.externalCacheDir)
            mTvCache.text = CommonHelper.convertDirSize(
                mContext.cacheDir,
                mContext.externalCacheDir
            )
        }
        //提供issues
        mLlIssues.onClick {
            mContext.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(string(R.string.UrlIssues))
                )
            )
        }
        //退出登陆
        mCvLoginOut.onClick {
            mPresenter.loginOut()
        }

    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
    }


    override fun onLoginOutSuccess() {
        sp().remove(Constant.SharedPreferences.COOKIE)
        ARouterClient.startLogin()
    }


}
