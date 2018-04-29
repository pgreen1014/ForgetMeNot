package com.pgreen.forgetmenot.storage

import com.pgreen.forgetmenot.data.TodoItem

/**
 * Temporary object for storing data and testing presenter and ui interfaces until database is built
 */
//TODO replace with Room database
object TodoListStorageObject: TodoListStorage {

    private val todoItems: MutableList<TodoItem> = mutableListOf()

    override fun getTodoItems(): List<TodoItem> {
        return todoItems
    }

    override fun saveNewTodoItem(item: TodoItem) {
        todoItems.add(item)
    }

    override fun updateTodoItem(item: TodoItem) {
        for (storedItem: TodoItem in todoItems) {
            updateItem(storedItem, item)
        }
    }

    private fun updateItem(storedItem: TodoItem, updateItem: TodoItem) {
        if (storedItem.id == updateItem.id) {
            val storedIndex = todoItems.indexOf(storedItem)
            todoItems.removeAt(storedIndex)
            todoItems.add(storedIndex, updateItem)
        }
    }

    override fun deleteTodoItem(item: TodoItem) {
        todoItems.remove(item)
    }

}