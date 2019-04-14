package com.memo.mine.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.iframe.base.adapter.BaseAdapter
import com.memo.iframe.tools.ext.drawable
import com.memo.mine.R
import com.memo.mine.config.entity.MineData

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-14 21:34
 */
class MineAdapter : BaseAdapter<MineData, BaseViewHolder>(R.layout.item_mine) {

    override fun initialize(helper: BaseViewHolder, item: MineData) {
        helper.addOnClickListener(R.id.mCvItem)
        val mTvItem = helper.getView<TextView>(R.id.mTvItem)
        mTvItem.setCompoundDrawablesWithIntrinsicBounds(
            null,
            helper.itemView.drawable(item.iconRes),
            null,
            null
        )
        mTvItem.text = item.title
    }
}