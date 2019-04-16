package com.memo.login.ui.activity.login

import android.content.Intent
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ActivityUtils
import com.flyco.tablayout.listener.OnTabSelectListener
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.iframe.base.adapter.BaseFragmentPagerAdapter
import com.memo.iframe.config.constant.Constant
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.arouter.ARouterPath
import com.memo.iframe.tools.ext.edit
import com.memo.iframe.tools.ext.sp
import com.memo.login.R
import com.memo.login.ui.fragment.signin.SignInFragment
import com.memo.login.ui.fragment.signup.SignUpFragment
import kotlinx.android.synthetic.main.activity_login.*

/**
 * title:登陆界面
 * describe: 账号 zhoumemo 密码 12345678
 *
 * @author zhou
 * @date 2019-04-12 17:54
 */
@Route(path = ARouterPath.Login.LoginActivity)
class LoginActivity : BaseMvpActivity<LoginContract.View, LoginPresenter>(), LoginContract.View {


    private val mSignInFragment: SignInFragment by lazy { SignInFragment() }
    private val mSignUpFragment: SignUpFragment by lazy { SignUpFragment() }

    private val mFragments: ArrayList<Fragment> by lazy {
        arrayListOf<Fragment>(
            mSignInFragment,
            mSignUpFragment
        )
    }

    private val mTitles: Array<String> by lazy { arrayOf("SIGN IN", "SIGN UP") }

    private val mAdapter: BaseFragmentPagerAdapter<Fragment> by lazy {
        BaseFragmentPagerAdapter<Fragment>(
            supportFragmentManager
        )
    }


    override fun transparentStatusBar(): Boolean = true

    override fun showStatusView(): Boolean = false

    override fun showTitleView(): Boolean = false

    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): LoginPresenter = LoginPresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_login

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
        //清楚本地缓存的数据
        sp().edit {
            remove(Constant.SharedPreferences.COOKIE)
            remove(Constant.SharedPreferences.USERNAME)
        }
    }

    /**
     * 进行初始化控件
     */
    override fun initView() {
        mTabLayout.setTabData(mTitles)

        mViewPager.run {
            mAdapter.setData(mFragments)
            adapter = mAdapter
        }
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {}
        })
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
        //除了最新的Activity关闭其他Activity
        ActivityUtils.finishAllActivitiesExceptNewest()
    }

    /**
     * 登陆
     */
    fun signIn(account: String, pwd: String) {
        mPresenter.signIn(account, pwd)
    }

    /**
     * 注册
     */
    fun signUp(account: String, pwd: String, rePwd: String) {
        mPresenter.signUp(account, pwd, rePwd)
    }


    /**
     * 登陆成功
     */
    override fun onSignInSuccess() {
        ARouterClient.startMain()
        finish()
    }

    /**
     * 登陆流程失败了
     */
    override fun onFailure() {
        if (mViewPager.currentItem == 0) {
            mSignInFragment.finishSignIn()
        } else {
            mSignUpFragment.finishSignUP()
        }
    }

    /**
     * 点击返回键退出应用
     */
    override fun onBackPressed() {
        ActivityUtils.finishAllActivities(true)
    }
}
