package com.memo.article.ui.adapter

import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexboxLayoutManager
import com.memo.article.R
import com.memo.article.config.entity.Tree
import com.memo.iframe.base.adapter.BaseAdapter

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-15 15:10
 */
class NaviAdapter : BaseAdapter<Tree, BaseViewHolder>(R.layout.item_navi) {

    override fun initialize(helper: BaseViewHolder, item: Tree) {
        helper.setText(R.id.mTvTitle, "----  ${item.name}  ----")
        val mRvList: RecyclerView = helper.getView(R.id.mRvList)
        mRvList.layoutManager = FlexboxLayoutManager(mContext)
        val mAdapter = NaviChildAdapter(item.children)
        mRvList.adapter = mAdapter
        mAdapter.addOnItemClickListener { view, position ->
            mOnCustomItemClickListener?.onItemClick(view, helper.adapterPosition, position)
        }
    }

}