package com.pgreen.forgetmenot.storage

import com.pgreen.forgetmenot.data.TodoItem

interface TodoItemsDataSource {

    interface LoadItemsCallback {
       fun onItemsLoaded(items: List<TodoItem>)
       fun onItemsUnavailable()
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
        fun onItemDeleted(item: TodoItem)
        fun onItemUnavailable()
    }

    interface ItemsDeltedCallback {
        fun onItemsDeleted()
        fun onItemsUnavailable()
    }

    fun loadTodoItems(callback: LoadItemsCallback)
    fun getItem(id: String, callback: GetItemCallback)
    fun updateItem(item: TodoItem, callback: ItemUpdatedCallback)
    fun deleteItem(id: String, callback: ItemDeletedCallback)
    fun deleteAllItems(callback: ItemsDeltedCallback)
}