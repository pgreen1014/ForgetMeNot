package com.pgreen.forgetmenot.data

import org.junit.Assert.assertEquals
import org.junit.Test

class TodoItemTest {

    @Test
    fun can_save_and_retrieve_itemName() {
        val placeTypes = listOf(GooglePlaceType.ATM, GooglePlaceType.BEAUTY_SALON)
        val item = TodoItem("Soap", placeTypes)
        assertEquals("Soap", item.name)
    }

    @Test
    fun can_save_and_retrieve_placeTypes() {
        val placeTypes = listOf(GooglePlaceType.ATM, GooglePlaceType.BEAUTY_SALON)
        val item = TodoItem("Soap", placeTypes)

        assertEquals(placeTypes, item.placeTypes)
    }

}