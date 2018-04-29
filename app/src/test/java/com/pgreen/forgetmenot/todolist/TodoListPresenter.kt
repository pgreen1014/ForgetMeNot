package com.pgreen.forgetmenot.todolist

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage
import org.junit.Assert.assertEquals
import org.junit.Test

class TodoListPresenter {

    val mockStorage = mock<TodoListStorage> { }
    val mockView = mock<TodoItemsListContract.View> { }
    val presenter = TodoItemsListPresenter(mockView, mockStorage)

    @Test
    fun onAddTodoItemsClicked_calls_TodoItemsListContract_Views_launchTodoItemsActivity() {
        presenter.onAddTodoItemsClicked()
        verify(mockView).launchAddTodoItemsActivity(null)
    }

    @Test
    fun onItemOptionsClicked_calls_views_toast_function() {
        presenter.onItemOptionsClicked(1)
        verify(mockView).showToast("Item 1 clicked!")
    }

    @Test
    fun onEditItemClicked_calls_launchAddTodoItemsActivity_with_TodoItem_argument() {
        val editItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))
        presenter.onEditItemClicked(editItem)
        verify(mockView).launchAddTodoItemsActivity(editItem)
    }

    @Test
    fun onEditItemClicked_tells_view_to_update_list() {
        val editItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))
        presenter.onEditItemClicked(editItem)
        verify(mockView).updateTodoList()
    }

    @Test
    fun onDeleteItemClicked_deletes_item_from_storage() {
        val deleteItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))
        presenter.onDeleteItemClicked(deleteItem)
        verify(mockStorage).deleteTodoItem(deleteItem)
    }

    @Test
    fun onDeleteItemClicked_tells_view_to_update_list() {
        val deleteItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))
        presenter.onDeleteItemClicked(deleteItem)
        verify(mockView).updateTodoList()
    }

    @Test
    fun getStoredTodoItems_returns_todo_items_from_storage() {
        val returnList = listOf<TodoItem>(
                TodoItem("Toothbrush", setOf(GooglePlaceType.ATM)),
                TodoItem("Coffee", setOf(GooglePlaceType.CONVENIENCE_STORE))
        )
        whenever(mockStorage.getTodoItems()).thenReturn(returnList)

        val result = presenter.getStoredTodoItems()
        assertEquals("presenter should return list stored in TodoListStorage implementation",
                returnList, result)
    }

}