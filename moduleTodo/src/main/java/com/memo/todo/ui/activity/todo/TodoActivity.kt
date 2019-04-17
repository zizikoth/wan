package com.memo.todo.ui.activity.todo

import android.content.Intent
import com.memo.iframe.base.activity.BaseMvpActivity
import com.memo.todo.R

class TodoActivity : BaseMvpActivity<TodoContract.View, TodoPresenter>(), TodoContract.View {
    /**
     * 绑定Presenter
     */
    override fun buildPresenter(): TodoPresenter = TodoPresenter()

    /**
     * 绑定布局id
     */
    override fun bindLayoutResId(): Int = R.layout.activity_todo

    /**
     * 进行初始化数据
     */
    override fun initData(intent: Intent) {
    }

    /**
     * 进行初始化控件
     */
    override fun initView() {
        getTitleView().setMoreIcon(R.drawable.ic_filter)
    }

    /**
     * 进行初始化监听
     */
    override fun initListener() {
    }

    /**
     * 开始进行业务操作
     */
    override fun start() {
    }

}
