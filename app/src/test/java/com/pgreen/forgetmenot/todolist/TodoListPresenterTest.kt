package com.pgreen.forgetmenot.todolist

import android.view.View
import com.nhaarman.mockito_kotlin.*
import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoItemsDataSource
import com.pgreen.forgetmenot.storage.local.TodoListLocalDataSource
import org.junit.Assert.assertEquals
import org.junit.Test

class TodoListPresenterTest {

    val mockItemView = mock<View> {  }
    val mockStorage = mock<TodoItemsDataSource> { }
    val mockView = mock<TodoItemsListContract.View> { }
    val presenter = TodoItemsListPresenter(mockView, mockStorage)

    @Test
    fun onAddTodoItemsClicked_calls_TodoItemsListContract_Views_launchTodoItemsActivity() {
        presenter.onAddTodoItemsClicked()
        verify(mockView).launchAddTodoItemsActivity(null, null)
    }

    @Test
    fun onItemOptionsClicked_calls_views_showItemOptionsDialog() {
        val todoItem0 = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM))
        val todoItem1 = TodoItem("Coffee", setOf(GooglePlaceType.CONVENIENCE_STORE))
        val returnList = listOf(
                todoItem0,
                todoItem1
        )

        doAnswer {
            val callback = it.arguments[0] as TodoItemsDataSource.LoadItemsCallback
            callback.onItemsLoaded(returnList)
        }.whenever(mockStorage).loadTodoItems(any())

        presenter.onItemOptionsClicked(mockItemView, 1)
        verify(mockView).showItemOptionsDialog(mockItemView, todoItem1, 1)
    }

    @Test
    fun onEditItemClicked_calls_launchAddTodoItemsActivity_with_TodoItem_argument() {
        val editItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))
        presenter.onEditItemClicked(editItem, 0)
        verify(mockView).launchAddTodoItemsActivity(editItem, 0)
    }

    @Test
    fun onDeleteItemClicked_deletes_item_from_storage() {
        val deleteItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))
        presenter.onDeleteItemClicked(deleteItem)

        doAnswer {
            val callback = it.arguments[1] as TodoItemsDataSource.ItemDeletedCallback
            callback.onItemDeleted(deleteItem)
        }.whenever(mockStorage).deleteItem(any(), any())
    }

    @Test
    fun onDeleteItemClicked_tells_view_to_update_list() {
        val deleteItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))

        doAnswer {
            val callback = it.arguments[1] as TodoItemsDataSource.ItemDeletedCallback
            callback.onItemDeleted(deleteItem)
        }.whenever(mockStorage).deleteItem(any(), any())

        doAnswer {
            val callback = it.arguments[0] as TodoItemsDataSource.LoadItemsCallback
            callback.onItemsLoaded(listOf())
        }.whenever(mockStorage).loadTodoItems(any())

        presenter.onDeleteItemClicked(deleteItem)
        verify(mockView).updateTodoList(any())
    }

    @Test
    fun getStoredTodoItems_returns_todo_items_from_storage() {
        val returnList = listOf(
                TodoItem("Toothbrush", setOf(GooglePlaceType.ATM)),
                TodoItem("Coffee", setOf(GooglePlaceType.CONVENIENCE_STORE))
        )

        doAnswer {
            val callback = it.arguments[0] as TodoItemsDataSource.LoadItemsCallback
            callback.onItemsLoaded(returnList)
        }.whenever(mockStorage).loadTodoItems(any())

        presenter.getStoredTodoItems()

        verify(mockView).updateTodoList(returnList)
    }

    @Test
    fun getStoredTodoItems_tells_view_to_showNoItemsAvailable_when_no_data_is_loaded_from_storage() {
        doAnswer {
            val callback = it.arguments[0] as TodoItemsDataSource.LoadItemsCallback
            callback.onItemsUnavailable()
        }.whenever(mockStorage).loadTodoItems(any())

        presenter.getStoredTodoItems()

        verify(mockView).showNoItemsAvailable()
    }
}