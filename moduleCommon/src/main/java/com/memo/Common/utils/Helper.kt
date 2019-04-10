package com.memo.Common.utils

import android.app.Activity
import android.view.ViewGroup
import com.just.agentweb.AgentWeb

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-10 17:20
 */
object Helper {
    /**
     * 初始化AgentWeb
     */
    @JvmStatic
    fun initAgentWeb(activity: Activity, parent: ViewGroup, url: String): AgentWeb =
            AgentWeb.with(activity)
                    .setAgentWebParent(parent, ViewGroup.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .createAgentWeb()
                    .ready()
                    .go(url)
}