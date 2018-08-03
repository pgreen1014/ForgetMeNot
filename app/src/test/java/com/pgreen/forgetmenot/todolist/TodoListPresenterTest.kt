package com.pgreen.forgetmenot.todolist

import android.view.View
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage
import org.junit.Assert.assertEquals
import org.junit.Test

class TodoListPresenterTest {

    val mockItemView = mock<View> {  }
    val mockStorage = mock<TodoListStorage> { }
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
        val returnList = listOf<TodoItem>(
                todoItem0,
                todoItem1
        )
        whenever(mockStorage.getTodoItems()).thenReturn(returnList)

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
    fun onEditItemClicked_tells_view_to_update_list() {
        val editItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY))
        presenter.onEditItemClicked(editItem,0)
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