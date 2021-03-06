package com.pgreen.forgetmenot.addtodoitem

import com.nhaarman.mockito_kotlin.*
import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoItemsDataSource
import com.pgreen.forgetmenot.storage.local.TodoListLocalDataSource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AddTodoItemPresenterTest {

    private val mockStorage = mock<TodoItemsDataSource> {  }
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

        verify(mockStorage).addTodoItem(
                argThat { name == expectedItem.name && placeTypes == expectedItem.placeTypes },
                any())

    }

    @Test
    fun onSaveItemButtonClicked_does_not_update_item_in_storage_if_adding_new_item() {
        val expectedItem = TodoItem("Toothbrush",
                setOf(GooglePlaceType.CONVENIENCE_STORE, GooglePlaceType.GAS_STATION, GooglePlaceType.HOME_GOODS_STORE))

        presenter.onSaveItemButtonClicked()

        verify(mockStorage, never()).updateItem(
                argThat { name == expectedItem.name && placeTypes == expectedItem.placeTypes },
                any()
        )
    }

    @Test
    fun onSaveItemButtonClicked_updates_TodoItem_if_editing_item() {
        presenter.onGooglePlaceItemChecked(GooglePlaceType.GAS_STATION, true)
        val editItem = TodoItem("Toothbrush", setOf(GooglePlaceType.GAS_STATION))

        presenter.setItemToEdit(editItem, 0)
        presenter.onSaveItemButtonClicked()

        verify(mockStorage).updateItem(
                argThat { name == editItem.name && placeTypes == editItem.placeTypes },
                any()
        )
    }

    @Test
    fun onSaveItemButtonClicked_does_not_save_new_item_in_storage_if_editing() {
        val editItem = TodoItem("Toothbrush", setOf(GooglePlaceType.GAS_STATION))

        presenter.setItemToEdit(editItem, 0)
        presenter.onSaveItemButtonClicked()

        verify(mockStorage, never()).addTodoItem(
                argThat { name == editItem.name && placeTypes == editItem.placeTypes },
                any()
        )
    }

    @Test
    fun onGooglePlaceItemChecked_with_false_input_will_remove_previously_set_GooglePlace() {
        presenter.onGooglePlaceItemChecked(GooglePlaceType.ATM, true)
        presenter.onGooglePlaceItemChecked(GooglePlaceType.BAKERY, true)
        presenter.onGooglePlaceItemChecked(GooglePlaceType.ATM, false)
        presenter.onSaveItemButtonClicked()

        val expectedItem = TodoItem("Toothbrush", setOf(GooglePlaceType.BAKERY))

        verify(mockStorage).addTodoItem(
                argThat { name == expectedItem.name && placeTypes == expectedItem.placeTypes },
                any()
        )
    }

    @Test
    fun shouldGooglePlaceTypeBeCheckedForItemEditing_returns_true_if_GooglePlaceType_at_position_is_contained_in_editItemPlaces() {
        val placeType1 = presenter.getGooglePlaceTypes()[0]
        val placeType2 = presenter.getGooglePlaceTypes()[4]
        val editItemPlaceTypes = setOf(placeType1, placeType2)

        val result = presenter.shouldGooglePlaceTypeBeCheckedForItemEditing(0, editItemPlaceTypes)
                && presenter.shouldGooglePlaceTypeBeCheckedForItemEditing(4, editItemPlaceTypes)

        assertTrue("shouldGooglePlaceTypeBeCheckedForItemEditing() should return true if the GooglePlaceType at position is contained in the Set argument",
                result)
    }

    @Test
    fun shouldGooglePlaceTypeBeCheckedForItemEditing_returns_false_if_GooglePlaceType_at_position_is_not_contained_in_editItemPlaces() {
        val placeType1 = presenter.getGooglePlaceTypes()[0]
        val placeType2 = presenter.getGooglePlaceTypes()[4]
        val editItemPlaceTypes = setOf(placeType1, placeType2)

        val result = presenter.shouldGooglePlaceTypeBeCheckedForItemEditing(6, editItemPlaceTypes)

        assertFalse("shouldGooglePlaceTypeBeCheckedForItemEditing() should return false if the GooglePlaceType at position argument is not contained in the Set argument",
                result)
    }

    @Test
    fun setItemToEdit_sets_name_of_editing_item_in_view() {
        val editItem = TodoItem("Coffee", setOf(GooglePlaceType.GAS_STATION))

        presenter.setItemToEdit(editItem, 0)

        verify(mockView).setItemName(editItem.name)
    }

    @Test
    fun getGooglePlaceTypesForEditingItem_returns_GooglePlaceTypes_for_editItem() {
        val googlePlaces = setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY)
        val editItem = TodoItem("Toothbrush", googlePlaces)
        presenter.setItemToEdit(editItem, 0)

        val result = presenter.getGooglePlaceTypesForEditingItem()
        assertEquals("Set returned by getGooglePlaceTypesForEditingItem() should equal set in item being edited",
                googlePlaces, result)
    }

    @Test
    fun getGooglePlaceTypesForEditingItem_returns_null_if_no_item_is_being_edited() {
        val result = presenter.getGooglePlaceTypesForEditingItem()

        assertNull("getGooglePlaceTypesForEditingItem() should return null if no item is being edited", result)
    }

}