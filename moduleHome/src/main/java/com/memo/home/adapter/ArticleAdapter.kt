package com.memo.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.Common.R
import com.memo.iframe.tools.ext.toTimeSpanDescribe
import com.memo.iframe.config.entity.ArticleData

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-28 10:51
 */
class ArticleAdapter : BaseQuickAdapter<ArticleData, BaseViewHolder>(R.layout.item_home_article) {

    override fun convert(helper: BaseViewHolder, item: ArticleData?) {
        item?.let {
            helper.setText(R.id.mTvAuthor, it.author)
                .setText(R.id.mTvTime, it.publishTime.toTimeSpanDescribe())
                .setText(R.id.mTvContent, it.title)
                .setText(R.id.mTvTag, it.superChapterName)
        }
    }
}