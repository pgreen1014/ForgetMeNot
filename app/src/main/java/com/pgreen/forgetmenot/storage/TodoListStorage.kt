package com.pgreen.forgetmenot.storage

import com.pgreen.forgetmenot.data.TodoItem

interface TodoListStorage {
    fun getTodoItems(): List<TodoItem>
    fun saveNewTodoItem(item: TodoItem)
    fun updateTodoItem(item: TodoItem)
    fun deleteTodoItem(item: TodoItem)
}