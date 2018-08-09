package com.pgreen.forgetmenot.addtodoitem

import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem

interface AddTodoItemContract {

    interface View {
        fun showToast(message: String)
        fun finishActivity()
        fun getItemName(): String
        fun setItemName(itemName: String)
    }

    interface Presenter {
        fun getGooglePlaceTypes(): Array<GooglePlaceType>
        fun getGooglePlaceUIStringResourceForPosition(position: Int): Int
        fun onGooglePlaceItemChecked(placeType: GooglePlaceType, isChecked: Boolean)
        fun onSaveItemButtonClicked()
        fun onCloseMenuButtonClicked()
        fun shouldGooglePlaceTypeBeCheckedForItemEditing(position: Int, editItemPlaceTypes: Set<GooglePlaceType>): Boolean
        fun setItemToEdit(editItem: TodoItem?, editItemPosition: Int?)
        fun getGooglePlaceTypesForEditingItem(): Set<GooglePlaceType>?
    }

}