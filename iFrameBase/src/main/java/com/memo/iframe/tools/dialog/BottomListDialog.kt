package com.memo.iframe.tools.dialog

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import com.chad.library.adapter.base.BaseViewHolder
import com.memo.iframe.R
import com.memo.iframe.base.adapter.BaseAdapter
import com.memo.iframe.tools.ext.onClick
import com.memo.iframe.tools.utils.ClickHelper
import kotlinx.android.synthetic.main.dialog_bottom_list.view.*
import razerdp.basepopup.BasePopupWindow


/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 14:23
 */
class BottomListDialog constructor(context: Context, data: ArrayList<String> = arrayListOf()) :
    BasePopupWindow(context) {

    /**
     * 数据源 不过不用 一般都是用mAdapter.data
     */
    private var mData: ArrayList<String> = data

    /**
     * 适配器
     */
    private val mAdapter: BaseAdapter<String, BaseViewHolder> =
        object : BaseAdapter<String, BaseViewHolder>(R.layout.dialog_bottom_list_item) {
            override fun initialize(helper: BaseViewHolder, item: String) {
                helper.setText(R.id.mTvContent, item)
                    .setGone(R.id.mLine, helper.adapterPosition != mData.size)
            }
        }

    /**
     * 点击监听
     */
    private var mListener: OnItemClickListener? = null


    /**
     * 初始化
     */
    init {
        popupGravity = Gravity.BOTTOM
        initialize()
    }

    /**
     * 显示动画
     */
    override fun onCreateShowAnimation(): Animation {
        return getTranslateVerticalAnimation(1f, 0f, 300)
    }

    /**
     * 消失动画
     */
    override fun onCreateDismissAnimation(): Animation {
        return getTranslateVerticalAnimation(0f, 1f, 300)
    }

    /**
     * 创建布局
     */
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.dialog_bottom_list)
    }

    /**
     * 初始化
     */
    private fun initialize() {
        contentView.mTvClose.onClick { dismiss() }
        contentView.mRvContent.layoutManager = LinearLayoutManager(context)
        contentView.mRvContent.adapter = mAdapter
        mAdapter.setNewData(mData)
        mAdapter.setOnItemClickListener { _, _, position ->
            mListener?.onItemClick(position, mAdapter.data[position])
            dismiss()
        }
    }

    /**
     * 设置数据源
     */
    fun setData(data: ArrayList<String>): BottomListDialog {
        mAdapter.setNewData(data)
        return this
    }

    /**
     * 在末尾添加一个数据
     */
    fun addData(data: String): BottomListDialog {
        mAdapter.addData(data)
        return this
    }

    /**
     * 可以在中间插入一个数据
     */
    fun addData(position: Int, data: String): BottomListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.addData(position, data)
        }
        return this
    }

    /**
     * 删除一个数据
     */
    fun removeData(position: Int): BottomListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.remove(position)
        }
        return this
    }

    /**
     * 更新某一项的数据
     */
    fun updateData(position: Int, data: String): BottomListDialog {
        if (position < mAdapter.data.size) {
            mAdapter.data[position] = data
            mAdapter.notifyItemChanged(position)
        }
        return this
    }

    /**
     * 设置点击监听
     */
    fun setOnItemClickListener(method: (position: Int, item: String) -> Unit): BottomListDialog {

        mListener = object : OnItemClickListener {
            /**
             * 条目点击
             */
            override fun onItemClick(position: Int, item: String) {
                if (ClickHelper.isNotFastClick) {
                    method(position, item)
                }
            }
        }
        return this
    }

    /**
     * 简化展示方法
     */
    fun show() {
        showPopupWindow()
    }


}