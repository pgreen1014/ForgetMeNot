package com.pgreen.forgetmenot.todolist

import android.view.View
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.local.TodoListLocalDataSource

class TodoItemsListPresenter(val view: TodoItemsListContract.View, val localDataSource: TodoListLocalDataSource) : TodoItemsListContract.Presenter {

    private var updatedItemPosition: Int? = null

    override fun onAddTodoItemsClicked() {
        view.launchAddTodoItemsActivity(null, null)
    }

    override fun onItemOptionsClicked(itemView: View, itemPosition: Int) {
        val item = getStoredTodoItems()[itemPosition]
        view.showItemOptionsDialog(itemView, item, itemPosition)
    }

    override fun onEditItemClicked(item: TodoItem, position: Int) {
        view.launchAddTodoItemsActivity(item, position)
    }

    override fun onDeleteItemClicked(item: TodoItem) {
        localDataSource.deleteTodoItem(item)
        view.updateTodoList()
    }

    override fun getStoredTodoItems(): List<TodoItem> {
        return localDataSource.getTodoItems()
    }

    //TODO Unit Test Needed
    override fun storePositionOfUpdatedItem(position: Int?) {
        updatedItemPosition = position
    }

    //TODO Unit Test Needed
    override fun checkForUpdatedItems() {
        if (updatedItemPosition != null) {
            view.updateListForPosition(updatedItemPosition!!)
            updatedItemPosition = null
        }
    }

}