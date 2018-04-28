package com.pgreen.forgetmenot.todolist

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class TodoListPresenter {

    val mockView = mock<TodoItemsListContract.View> { }
    val presenter = TodoItemsListPresenter(mockView)

    @Test
    fun onAddTodoItemsClicked_calls_TodoItemsListContract_Views_launchTodoItemsActivity() {
        presenter.onAddTodoItemsClicked()
        verify(mockView).launchAddTodoItemsActivity()
    }

    @Test
    fun onItemOptionsClicked_calls_views_toast_function() {
        presenter.onItemOptionsClicked(1)
        verify(mockView).showToast("Item 1 clicked!")
    }

}