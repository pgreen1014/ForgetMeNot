package com.pgreen.forgetmenot.addtodoitem

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.main.MainActivity
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AddTodoItemPresenterTest {

    val mockView = mock<AddTodoItemContract.View> {  }
    val presenter = AddTodoItemPresenter(mockView)

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
    fun onCloseMenuButtonClicked_calls_view_moveToActivitywith_MainActivity_target() {
        presenter.onCloseMenuButtonClicked()
        verify(mockView).finishActivity()
    }
}