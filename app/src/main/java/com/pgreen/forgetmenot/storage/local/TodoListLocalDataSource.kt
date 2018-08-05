package com.pgreen.forgetmenot.storage.local

import com.pgreen.forgetmenot.data.TodoItem

interface TodoListLocalDataSource {
    fun getTodoItems(): List<TodoItem>
    fun saveNewTodoItem(item: TodoItem)
    fun updateTodoItem(item: TodoItem)
    fun deleteTodoItem(item: TodoItem)
}