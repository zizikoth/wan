package com.memo.mine.ui.activity.setting

import com.memo.iframe.base.mvp.IModel
import com.memo.iframe.base.mvp.IView
import com.memo.iframe.config.entity.EmptyResponse
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 00:24
 */
interface SettingContract {

    interface Model : IModel {
        fun loginOut(): Observable<EmptyResponse>
    }

    interface View : IView {
        fun onLoginOutSuccess()
    }

    interface Presenter {
        /**
         * 退出登陆
         */
        fun loginOut()
    }

}