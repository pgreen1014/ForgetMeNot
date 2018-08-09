package com.pgreen.forgetmenot.storage.local

import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoItemsDataSource

/**
 * Temporary object for storing data and testing presenter and ui interfaces until database is built
 */
//TODO replace with Room database
object TodoListStorageObject: TodoItemsDataSource {

    private val todoItems: MutableMap<String, TodoItem> = mutableMapOf()

    override fun loadTodoItems(callback: TodoItemsDataSource.LoadItemsCallback) {
        val listOfItems = todoItems.values.toList()

        if (listOfItems.isEmpty()) {
            callback.onItemsUnavailable()
        } else {
            callback.onItemsLoaded(listOfItems)
        }
    }

    override fun addTodoItem(item: TodoItem, callback: TodoItemsDataSource.AddItemCallback) {
        if (todoItems[item.id] != null) {
            callback.onError()
        } else {
            todoItems[item.id] = item
            callback.onItemAdded(item)
        }
    }

    override fun getItem(id: String, callback: TodoItemsDataSource.GetItemCallback) {
        val item = todoItems[id]

        if (item != null) {
            callback.onItemRetrieved(item)
        } else {
            callback.onItemUnavailable()
        }
    }

    override fun updateItem(item: TodoItem, callback: TodoItemsDataSource.ItemUpdatedCallback) {
        if (todoItems[item.id] != null) {
            todoItems[item.id] = item
            callback.onItemUpdated(item)
        } else {
            callback.onItemUnavailable()
        }
    }

    override fun deleteItem(id: String, callback: TodoItemsDataSource.ItemDeletedCallback) {
        val deleteItem = todoItems[id]
        if (deleteItem != null) {
            todoItems.remove(id)
            callback.onItemDeleted(deleteItem)
        } else {
            callback.onItemUnavailable()
        }
    }

    override fun deleteAllItems(callback: TodoItemsDataSource.ItemsDeletedCallback) {
        if (todoItems.isEmpty()) {
            callback.onItemsUnavailable()
        } else {
            callback.onItemsDeleted()
        }
    }
}