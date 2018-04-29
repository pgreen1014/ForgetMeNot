package com.pgreen.forgetmenot.addtodoitem

import com.pgreen.forgetmenot.data.GooglePlaceType

interface AddTodoItemContract {

    interface View {
        fun showToast(message: String)
        fun finishActivity()
        fun getItemName(): String
    }

    interface Presenter {
        fun getGooglePlaceTypes(): Array<GooglePlaceType>
        fun getGooglePlaceUIStringResourceForPosition(position: Int): Int
        fun onGooglePlaceItemChecked(placeType: GooglePlaceType, isChecked: Boolean)
        fun onSaveItemButtonClicked()
        fun onCloseMenuButtonClicked()
    }

}