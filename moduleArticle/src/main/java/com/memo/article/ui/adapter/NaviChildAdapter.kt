package com.memo.article.ui.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.memo.article.R
import com.memo.article.config.entity.Project
import com.memo.iframe.base.adapter.BaseAdapter
import com.memo.iframe.tools.utils.CommonHelper
import com.ruffian.library.widget.RTextView

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:20
 */
class NaviChildAdapter(data: List<Project>) :
    BaseAdapter<Project, BaseViewHolder>(R.layout.item_navi_children, data) {
    override fun initialize(helper: BaseViewHolder, item: Project) {
        if (helper.itemView is RTextView) {
            val mItemView: RTextView = helper.itemView as RTextView
            mItemView.text = item.name
            mItemView.setTextColor(CommonHelper.randomColor())
        }
    }
}