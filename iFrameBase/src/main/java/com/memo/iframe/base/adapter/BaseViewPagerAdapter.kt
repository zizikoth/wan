package com.memo.iframe.base.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * title:基础ViewPagerAdapter
 * tip:
 *
 * @author zhou
 * @date 2018/8/1 下午7:07
 */
class BaseViewPagerAdapter<T : View>(protected var context: Context) : PagerAdapter() {
    protected var mData: List<T>? = null

    fun setData(list: List<T>) {
        this.mData = list
        this.notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return if (mData != null) mData!!.size else 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mData!![position])
        return mData!![position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        if (obj is View) {
            container.removeView(obj)
        }
    }
}