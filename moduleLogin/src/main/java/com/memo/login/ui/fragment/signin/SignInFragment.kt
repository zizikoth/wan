package com.memo.login.ui.fragment.signin


import android.os.Bundle
import com.memo.iframe.base.fragment.BaseFragment
import com.memo.iframe.tools.ext.gone
import com.memo.iframe.tools.ext.onClick
import com.memo.iframe.tools.ext.visible
import com.memo.login.R
import com.memo.login.ui.activity.login.LoginActivity
import com.memo.login.utils.CheckHelper
import kotlinx.android.synthetic.main.fragment_sign_in.*

/**
 * title:登陆界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 15:32
 */
class SignInFragment : BaseFragment() {

    override fun showStatusView(): Boolean = false

    /**
     * 绑定布局
     */
    override fun bindLayoutResId(): Int = R.layout.fragment_sign_in

    /**
     * 进行初始化数据
     */
    override fun initData(bundle: Bundle?) {}

    /**
     * 进行初始化控件
     */
    override fun initView() {}

    /**
     * 进行初始化监听
     */
    override fun initListener() {
        mTvSignIn.onClick {
            val account = mEtAccount.text.toString().trim()
            val pwd = mEtPwd.text.toString().trim()
            if (CheckHelper.checkSignIn(account, pwd)) {
                //登陆
                if (mActivity is LoginActivity) {
                    mTvSignIn.isEnabled = false
                    mProgress.visible()
                    (mActivity as LoginActivity).signIn(account, pwd)
                }
            }
        }
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
    }

    /**
     * 登陆结束
     */
    fun finishSignIn() {
        mTvSignIn.isEnabled = true
        mProgress.gone()
    }
}
