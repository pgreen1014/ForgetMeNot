package com.pgreen.forgetmenot.todolist

import com.pgreen.forgetmenot.data.TodoItem

interface TodoItemsListContract {

    interface View {
        fun showToast(message: String)
        fun launchAddTodoItemsActivity(editItem: TodoItem?)
        fun updateTodoList()
    }

    interface Presenter {
        fun onAddTodoItemsClicked()
        fun onItemOptionsClicked(itemPosition: Int)
        fun onDeleteItemClicked(item: TodoItem)
        fun onEditItemClicked(item: TodoItem)
        fun getStoredTodoItems(): List<TodoItem>
    }

}