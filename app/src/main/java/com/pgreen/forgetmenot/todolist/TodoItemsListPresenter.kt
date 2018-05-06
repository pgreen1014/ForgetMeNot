package com.pgreen.forgetmenot.todolist

import android.view.View
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage

class TodoItemsListPresenter(val view: TodoItemsListContract.View, val storage: TodoListStorage) : TodoItemsListContract.Presenter {

    override fun onAddTodoItemsClicked() {
        view.launchAddTodoItemsActivity(null)
    }

    override fun onItemOptionsClicked(itemView: View, itemPosition: Int) {
        val item = getStoredTodoItems()[itemPosition]
        view.showItemOptionsDialog(itemView, item)
    }

    override fun onEditItemClicked(item: TodoItem) {
        view.launchAddTodoItemsActivity(item)
        view.updateTodoList()
    }

    override fun onDeleteItemClicked(item: TodoItem) {
        storage.deleteTodoItem(item)
        view.updateTodoList()
    }

    override fun getStoredTodoItems(): List<TodoItem> {
        return storage.getTodoItems()
    }

}