package com.pgreen.forgetmenot.todolist

import com.pgreen.forgetmenot.data.TodoItem

interface TodoItemsListContract {

    interface View {
        fun showToast(message: String)
        fun launchAddTodoItemsActivity(editItem: TodoItem?, position: Int?)
        fun updateTodoList()
        fun showItemOptionsDialog(itemView: android.view.View, item: TodoItem, position: Int)
        fun updateListForPosition(position: Int)
    }

    interface Presenter {
        fun onAddTodoItemsClicked()
        fun onItemOptionsClicked(itemView: android.view.View, itemPosition: Int)
        fun onDeleteItemClicked(item: TodoItem)
        fun onEditItemClicked(item: TodoItem, position: Int)
        fun getStoredTodoItems(): List<TodoItem>
        fun storePositionOfUpdatedItem(position: Int?)
        fun checkForUpdatedItems()
    }

}