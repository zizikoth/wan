package com.memo.mine.ui.activity.setting

import com.memo.iframe.config.api.convertEmpty
import com.memo.iframe.config.entity.EmptyResponse
import com.memo.mine.config.api.mMineApi
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 00:27
 */
class SettingModel : SettingContract.Model {

    override fun loginOut(): Observable<EmptyResponse> =
        mMineApi.loginOut().convertEmpty()
}