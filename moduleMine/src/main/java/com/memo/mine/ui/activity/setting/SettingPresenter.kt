package com.memo.mine.ui.activity.setting

import com.memo.iframe.base.mvp.BasePresenter
import com.memo.iframe.config.api.execute

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 00:27
 */
class SettingPresenter : BasePresenter<SettingModel, SettingContract.View>(),
    SettingContract.Presenter {
    /**
     * 绑定Model
     */
    override fun buildModel(): SettingModel = SettingModel()

    /**
     * 退出登陆
     */
    override fun loginOut() {
        mModel.loginOut().execute(mView) {
            mView.onLoginOutSuccess()
        }
    }
}