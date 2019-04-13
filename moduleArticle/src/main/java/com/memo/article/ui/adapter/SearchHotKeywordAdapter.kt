package com.memo.article.ui.adapter

import com.chad.library.adapter.base.BaseViewHolder
import com.memo.article.R
import com.memo.article.config.entity.HotKeyword
import com.memo.iframe.base.adapter.BaseAdapter
import com.memo.iframe.tools.utils.CommonHelper
import com.ruffian.library.widget.RTextView

/**
 * title:搜索热词的适配器
 * describe:
 *
 * @author zhou
 * @date 2019-04-13 22:48
 */
class SearchHotKeywordAdapter :
    BaseAdapter<HotKeyword, BaseViewHolder>(R.layout.item_search_hot_keyword) {

    override fun initialize(helper: BaseViewHolder, item: HotKeyword) {
        if (helper.itemView is RTextView) {
            val mItemView: RTextView = helper.itemView as RTextView
            mItemView.text = item.name
            mItemView.setTextColor(CommonHelper.randomColor())
        }
    }
}