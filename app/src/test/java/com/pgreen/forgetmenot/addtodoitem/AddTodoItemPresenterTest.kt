package com.pgreen.forgetmenot.addtodoitem

import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AddTodoItemPresenterTest {

    private val mockStorage = mock<TodoListStorage> {  }
    private val mockView = mock<AddTodoItemContract.View> {
        whenever(it.getItemName()).thenReturn("Toothbrush")
    }

    private val presenter = AddTodoItemPresenter(mockView, mockStorage)

    @Test
    fun getGooglePlaceTypes_returns_array_containing_values_of_GooglePlaceType_enum() {
        val expected = GooglePlaceType.values()
        val actual = presenter.getGooglePlaceTypes()
        assertTrue(actual.contentDeepEquals(expected))
    }

    @Test
    fun getGooglePlaceTypeForPosition_returns_GooglePlaceType_at_position() {
        val position = 5
        val expected = GooglePlaceType.values()[position].getUIPresentationStringID()
        val result = presenter.getGooglePlaceUIStringResourceForPosition(position)

        assertEquals(expected, result)
    }

    @Test
    fun onSaveItemButtonClicked_calls_view_moveToActivity_with_MainActivity_target() {
        presenter.onSaveItemButtonClicked()
        verify(mockView).finishActivity()
    }

    @Test
    fun onCloseMenuButtonClicked_calls_view_moveToActivity_with_MainActivity_target() {
        presenter.onCloseMenuButtonClicked()
        verify(mockView).finishActivity()
    }

    @Test
    fun onSaveItemButtonClicked_stores_TodoItem_with_item_from_view() {
        presenter.onGooglePlaceItemChecked(GooglePlaceType.CONVENIENCE_STORE, true)
        presenter.onGooglePlaceItemChecked(GooglePlaceType.GAS_STATION, true)
        presenter.onGooglePlaceItemChecked(GooglePlaceType.HOME_GOODS_STORE, true)
        presenter.onSaveItemButtonClicked()

        val expectedItem = TodoItem("Toothbrush",
                setOf(GooglePlaceType.CONVENIENCE_STORE, GooglePlaceType.GAS_STATION, GooglePlaceType.HOME_GOODS_STORE))

        verify(mockStorage).saveNewTodoItem(
                argThat { name == expectedItem.name && placeTypes == expectedItem.placeTypes }
        )
    }

    @Test
    fun onGooglePlaceItemChecked_with_false_input_will_remove_previously_set_GooglePlace() {
        presenter.onGooglePlaceItemChecked(GooglePlaceType.ATM, true)
        presenter.onGooglePlaceItemChecked(GooglePlaceType.BAKERY, true)
        presenter.onGooglePlaceItemChecked(GooglePlaceType.ATM, false)
        presenter.onSaveItemButtonClicked()

        val expectedItem = TodoItem("Toothbrush", setOf(GooglePlaceType.BAKERY))

        verify(mockStorage).saveNewTodoItem(
                argThat { name == expectedItem.name && placeTypes == expectedItem.placeTypes }
        )
    }
}