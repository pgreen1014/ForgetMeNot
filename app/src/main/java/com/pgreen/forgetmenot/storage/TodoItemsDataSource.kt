package com.pgreen.forgetmenot.storage

import com.pgreen.forgetmenot.data.TodoItem

interface TodoItemsDataSource {

    interface LoadItemsCallback {
       fun onItemsLoaded(items: List<TodoItem>)
       fun onItemsUnavailable()
    }

    interface AddItemCallback {
        fun onItemAdded(addedItem: TodoItem)
        fun onError()
    }

    interface GetItemCallback {
        fun onItemRetrieved(item: TodoItem)
        fun onItemUnavailable()
    }

    interface ItemUpdatedCallback {
        fun onItemUpdated(item: TodoItem)
        fun onItemUnavailable()
    }

    interface ItemDeletedCallback {
        fun onItemDeleted(deletedItem: TodoItem)
        fun onItemUnavailable()
    }

    interface ItemsDeletedCallback {
        fun onItemsDeleted()
        fun onItemsUnavailable()
    }

    fun loadTodoItems(callback: LoadItemsCallback)
    fun addTodoItem(item: TodoItem, callback: AddItemCallback)
    fun getItem(id: String, callback: GetItemCallback)
    fun updateItem(item: TodoItem, callback: ItemUpdatedCallback)
    fun deleteItem(id: String, callback: ItemDeletedCallback)
    fun deleteAllItems(callback: ItemsDeletedCallback)
}