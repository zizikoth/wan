package com.memo.todo.ui.activity.todo

import com.memo.iframe.base.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-04-17 15:04
 */
class TodoPresenter : BasePresenter<TodoModel, TodoContract.View>(), TodoContract.Presenter {
    /**
     * 绑定Model
     */
    override fun buildModel(): TodoModel = TodoModel()


}