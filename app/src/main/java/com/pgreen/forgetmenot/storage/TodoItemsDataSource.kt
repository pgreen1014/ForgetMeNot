package com.pgreen.forgetmenot.storage

import com.pgreen.forgetmenot.data.TodoItem

interface TodoItemsDataSource {

   interface LoadTodoItemsCallback {
      fun onTodoItemsLoaded(items: List<TodoItem>)
      fun onNoItemsAvailable()
   }

   fun loadTodoItems(callback: LoadTodoItemsCallback)
}