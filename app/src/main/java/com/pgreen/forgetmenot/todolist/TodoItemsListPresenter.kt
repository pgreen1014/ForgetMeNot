package com.pgreen.forgetmenot.todolist

import android.view.View
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoItemsDataSource

class TodoItemsListPresenter(val view: TodoItemsListContract.View, private val localDataSource: TodoItemsDataSource) : TodoItemsListContract.Presenter {

    override fun onAddTodoItemsClicked() {
        view.launchAddTodoItemsActivity(null, null)
    }

    override fun onItemOptionsClicked(itemView: View, itemPosition: Int) {
        localDataSource.loadTodoItems(object : TodoItemsDataSource.LoadItemsCallback {
            override fun onItemsLoaded(items: List<TodoItem>) {
                val item = items[itemPosition]
                view.showItemOptionsDialog(itemView, item, itemPosition)
            }

            override fun onItemsUnavailable() {

            }
        })
    }

    override fun onEditItemClicked(item: TodoItem, position: Int) {
        view.launchAddTodoItemsActivity(item, position)
    }

    override fun onDeleteItemClicked(item: TodoItem) {
        localDataSource.deleteItem(item.id, object : TodoItemsDataSource.ItemDeletedCallback {
            override fun onItemUnavailable() {

            }

            override fun onItemDeleted(deletedItem: TodoItem) {
                getStoredTodoItems()
            }
        })
    }

    override fun getStoredTodoItems() {
        localDataSource.loadTodoItems(object : TodoItemsDataSource.LoadItemsCallback {
            override fun onItemsLoaded(items: List<TodoItem>) {
                view.updateTodoList(items)
                view.showTodoList()
            }

            override fun onItemsUnavailable() {
                view.showNoItemsAvailable()
            }
        })
    }

    //TODO load individual items rather than entire list
    override fun checkForUpdatedItems() {
        getStoredTodoItems()
    }

}