package com.memo.login.ui.fragment.signup


import android.os.Bundle
import com.memo.iframe.base.fragment.BaseFragment
import com.memo.iframe.tools.ext.onClick
import com.memo.login.R
import com.memo.login.ui.activity.login.LoginActivity
import com.memo.login.utils.CheckHelper
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * title:注册界面
 * describe:
 *
 * @author zhou
 * @date 2019-04-12 15:26
 */
class SignUpFragment : BaseFragment() {

    override fun showStatusView(): Boolean = false

    /**
     * 绑定布局
     */
    override fun bindLayoutResId(): Int = R.layout.fragment_sign_up

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
        mTvSignUp.onClick {
            val account = mEtAccount.text.toString().trim()
            val pwd = mEtPwd.text.toString().trim()
            val rePwd = mEtVPwd.text.toString().trim()
            if (CheckHelper.checkSignUp(account, pwd, rePwd)) {
                //登陆
                if (mActivity is LoginActivity) {
                    (mActivity as LoginActivity).signUp(account, pwd, rePwd)
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


