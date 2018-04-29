package com.pgreen.forgetmenot.todolist

interface TodoItemsListContract {

    interface View {
        fun updateTodoItems()
        fun showToast(message: String)
        fun launchAddTodoItemsActivity()
        fun updateTodoList()
    }

    interface Presenter {
        fun onAddTodoItemsClicked()
        fun onItemOptionsClicked(itemPosition: Int)
        fun onDeleteItemClicked()
        fun onEditItemClicked()
        fun loadTodoItems()
    }

}