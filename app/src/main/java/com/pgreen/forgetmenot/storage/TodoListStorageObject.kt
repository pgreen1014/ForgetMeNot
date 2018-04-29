package com.pgreen.forgetmenot.storage

import com.pgreen.forgetmenot.data.TodoItem

/**
 * Temporary object for storing data and testing presenter and ui interfaces until database is built
 */
//TODO replace with Room database
object TodoListStorageObject: TodoListStorage {

    override fun getTodoItems(): List<TodoItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveNewTodoItem(item: TodoItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTodoItem(item: TodoItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTodoItem(item: TodoItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}