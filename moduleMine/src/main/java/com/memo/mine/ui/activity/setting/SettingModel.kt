package com.memo.mine.ui.activity.setting

import com.memo.iframe.config.api.ApiErrorCode
import com.memo.iframe.config.api.ApiException
import com.memo.iframe.config.entity.EmptyResponse
import com.memo.mine.config.api.MineApi
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
        MineApi.loginOut()
            .flatMap {
                when (it.errorCode) {
                    //请求成功
                    ApiErrorCode.SUCCESS ->
                        Observable.just(EmptyResponse())
                    //Token失效 重新登陆
                    ApiErrorCode.TOKEN_UN_LOGIN ->
                        Observable.error(ApiException(it.errorCode, it.errorMsg))
                    //服务器异常
                    else ->
                        Observable.error(ApiException(it.errorCode, it.errorMsg))
                }
            }
}