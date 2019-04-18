package com.memo.article.ui.adapter

import android.text.Html
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.article.R
import com.memo.article.config.entity.ArticleData
import com.memo.iframe.base.adapter.BaseAdapter
import com.memo.iframe.widget.textview.AlignTextView

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-17 10:03
 */
@Suppress("DEPRECATION")
class FavoriteAdapter : BaseAdapter<ArticleData, BaseViewHolder>(R.layout.item_favorite) {

    override fun initialize(helper: BaseViewHolder, item: ArticleData) {

        helper.getView<AlignTextView>(R.id.mTvTitle).reset()
        helper.getView<AlignTextView>(R.id.mTvDesc).reset()

        helper.addOnClickListener(R.id.mTvContent)
            .addOnClickListener(R.id.mTvDelete)
            .setText(R.id.mTvAutor, item.author)
            .setText(R.id.mTvTitle, Html.fromHtml(item.title).toString())
            .setText(R.id.mTvDesc, Html.fromHtml(item.desc).toString())
            .setGone(R.id.mTvDesc, item.desc.isNotEmpty())
            .setText(R.id.mTvChapter, item.chapterName)
            .setText(R.id.mTvTime, item.niceDate)
    }
}