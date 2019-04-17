package com.memo.article.ui.adapter

import android.text.Html
import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.article.R
import com.memo.article.config.api.mArticleApi
import com.memo.article.config.entity.ArticleData
import com.memo.iframe.base.adapter.BaseAdapter
import com.memo.iframe.config.api.convertEmpty
import com.memo.iframe.config.api.execute
import com.memo.iframe.tools.arouter.ARouterClient
import com.memo.iframe.tools.ext.string
import com.memo.iframe.widget.textview.AlignTextView
import com.memo.iframe.widget.textview.SlantedTextView

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-28 10:51
 */
@Suppress("DEPRECATION")
class ArticleAdapter : BaseAdapter<ArticleData, BaseViewHolder>(R.layout.item_article) {

    init {
        addOnItemChildClickListener { view, position ->
            if (view.id == R.id.mIvFavorite) {
                val article = data[position]
                val isCollect = article.collect
                (view as ImageView).setImageResource(
                    if (!isCollect) {
                        article.collect = true
                        //收藏
                        mArticleApi.addFavoriteArticle(article.id.toString()).convertEmpty()
                            .execute {
                                LogUtils.iTag("favorite", "${article.id} ${article.collect}")
                            }
                        R.drawable.ic_favorited
                    } else {
                        article.collect = false
                        //取消收藏
                        mArticleApi.removeFavoriteArticle(article.id.toString()).convertEmpty()
                            .execute {
                                LogUtils.iTag("favorite", "${article.id} ${article.collect}")
                            }
                        R.drawable.ic_favorite
                    }
                )
            }
        }

        addOnItemClickListener { _, position ->
            val articleData: ArticleData = data[position]
            ARouterClient.startAgentWeb(articleData.title, articleData.link)
        }
    }

    override fun initialize(helper: BaseViewHolder, item: ArticleData) {

        helper.getView<AlignTextView>(R.id.mTvTitle).reset()
        helper.getView<AlignTextView>(R.id.mTvDesc).reset()

        helper.addOnClickListener(R.id.mIvFavorite)
            .setGone(R.id.mTvTop, item.isTop)
            .setText(R.id.mTvAutor, item.author)
            .setText(R.id.mTvTitle, Html.fromHtml(item.title).toString())
            .setText(R.id.mTvDesc, Html.fromHtml(item.desc).toString())
            .setGone(R.id.mTvDesc, item.desc.isNotEmpty())
            .setText(R.id.mTvChapter, item.chapterName)
            .setText(R.id.mTvTime, item.niceDate)
            .setImageResource(
                R.id.mIvFavorite, if (item.collect) {
                    R.drawable.ic_favorited
                } else {
                    R.drawable.ic_favorite
                }
            )

        helper.getView<SlantedTextView>(R.id.mTvLabel).text = if (item.superChapterName.isEmpty()) {
            mContext.string(R.string.Android)
        } else {
            item.superChapterName
        }

    }
}