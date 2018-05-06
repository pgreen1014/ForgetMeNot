package com.pgreen.forgetmenot.todolist.itemoptionmenu

import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage

class ItemOptionsPopupMenuPresenter(
        private val view: ItemOptionsPopupMenuContract.View,
        private val storage: TodoListStorage
): ItemOptionsPopupMenuContract.Presenter {

    override fun onDeleteItemOptionClicked(item: TodoItem) {
        storage.deleteTodoItem(item)
    }

    override fun onEditItemOptionClicked(item: TodoItem) {
        view.openEditItemView(item)
    }
}