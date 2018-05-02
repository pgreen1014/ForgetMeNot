package com.pgreen.forgetmenot.todolist

import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage

class TodoItemsListPresenter(val view: TodoItemsListContract.View, val storage: TodoListStorage) : TodoItemsListContract.Presenter {

    override fun onAddTodoItemsClicked() {
        view.launchAddTodoItemsActivity(null)
    }

    override fun onItemOptionsClicked(itemPosition: Int) {
//        view.showToast("Item $itemPosition clicked!")
        view.showItemOptionsDialog()
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