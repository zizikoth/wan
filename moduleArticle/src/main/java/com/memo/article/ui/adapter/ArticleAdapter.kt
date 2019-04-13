package com.memo.article.ui.adapter

import android.text.Html
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.article.R
import com.memo.article.config.entity.ArticleData
import com.memo.iframe.base.adapter.BaseAdapter
import com.memo.iframe.tools.ext.toTimeSpanDescribe

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-28 10:51
 */
@Suppress("DEPRECATION")
class ArticleAdapter : BaseAdapter<ArticleData, BaseViewHolder>(R.layout.item_home_article) {

    override fun initialize(helper: BaseViewHolder, item: ArticleData) {
        helper.setText(R.id.mTvAuthor, item.author)
            .setText(R.id.mTvTime, item.publishTime.toTimeSpanDescribe())
            .setText(R.id.mTvContent, Html.fromHtml(item.title))
            .setText(R.id.mTvTag, item.superChapterName)
    }
}