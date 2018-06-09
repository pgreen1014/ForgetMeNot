package com.pgreen.forgetmenot.data

import android.os.Parcel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Test

class TodoItemTest {

    private val mockParcel = mock<Parcel> {  }

    @Test
    fun can_save_and_retrieve_itemName() {
        val placeTypes = setOf(GooglePlaceType.ATM, GooglePlaceType.BEAUTY_SALON)
        val item = TodoItem("Soap", placeTypes)
        assertEquals("Soap", item.name)
    }

    @Test
    fun can_save_and_retrieve_placeTypes() {
        val placeTypes = setOf(GooglePlaceType.ATM, GooglePlaceType.BEAUTY_SALON)
        val item = TodoItem("Soap", placeTypes)

        assertEquals(placeTypes, item.placeTypes)
    }

    @Test
    fun has_unique_identifier() {
        val placeTypes = setOf(GooglePlaceType.ATM, GooglePlaceType.BEAUTY_SALON)
        val item = TodoItem("Soap", placeTypes)

        assertNotNull(item.id)
    }

    @Test
    fun writeToParcel_writes_name_of_TodoItem_to_Parcel() {
        val itemName = "Toothbrush"
        val todoItem = TodoItem(
                itemName,
                setOf(GooglePlaceType.ATM),
                "1")

        todoItem.writeToParcel(mockParcel, 0)

        verify(mockParcel).writeString(itemName)
    }

    @Test
    fun writeToParcel_writes_placeTypes_as_mutable_list_to_Parcel() {
        val placeTypesSet = setOf(GooglePlaceType.ATM, GooglePlaceType.BAKERY)
        val todoItem = TodoItem("Bread", placeTypesSet, "1")

        todoItem.writeToParcel(mockParcel, 0)

        verify(mockParcel).writeList(placeTypesSet.toMutableList())
    }

    @Test
    fun writeToParcel_writes_id_as_string_to_Parcel() {
        val id = "1"
        val todoItem = TodoItem(
                "Toothbrush",
                setOf(GooglePlaceType.ATM),
                id)

        todoItem.writeToParcel(mockParcel, 0)

        verify(mockParcel).writeString(id)
    }

    @Test
    fun describeContents_returns_0() {
        val todoItem = TodoItem("Toothbrush", setOf(GooglePlaceType.ATM))

        val result = todoItem.describeContents()

        assertEquals(0, result)
    }

    @Test
    fun new_array_returns_an_array_of_nulls_equal_to_size_of_argument() {
        val size = 5
        val array = TodoItem.newArray(size)

        assertEquals(size, array.size)

        for (item in array) {
            assertNull(item)
        }
    }

}