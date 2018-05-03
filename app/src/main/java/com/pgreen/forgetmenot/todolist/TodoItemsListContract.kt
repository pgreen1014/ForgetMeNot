package com.pgreen.forgetmenot.todolist

import com.pgreen.forgetmenot.data.TodoItem

interface TodoItemsListContract {

    interface View {
        fun showToast(message: String)
        fun launchAddTodoItemsActivity(editItem: TodoItem?)
        fun updateTodoList()
        fun showItemOptionsDialog(itemView: android.view.View)
    }

    interface Presenter {
        fun onAddTodoItemsClicked()
        fun onItemOptionsClicked(itemView: android.view.View, itemPosition: Int)
        fun onDeleteItemClicked(item: TodoItem)
        fun onEditItemClicked(item: TodoItem)
        fun getStoredTodoItems(): List<TodoItem>
    }

}