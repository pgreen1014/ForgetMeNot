package com.pgreen.forgetmenot.todolist.itemoptionmenu

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage
import org.junit.Test

class ItemOptionsPopupMenuPresenterTest {

    val mockStorage = mock<TodoListStorage> {  }
    val mockView = mock<ItemOptionsPopupMenuContract.View> {  }
    val presenter = ItemOptionsPopupMenuPresenter(mockView, mockStorage)

    @Test
    fun onDeleteItemOptionsClicked_deletes_storage_for_item_passed_as_argument() {
        val item = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM))
        presenter.onDeleteItemOptionClicked(item)
        verify(mockStorage).deleteTodoItem(item)
    }

    @Test
    fun onEditItemOptionClicked_tells_view_to_open_edit_item_view_for_item() {
        val item = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM))
        presenter.onEditItemOptionClicked(item)
        verify(mockView).openEditItemView(item)
    }
}