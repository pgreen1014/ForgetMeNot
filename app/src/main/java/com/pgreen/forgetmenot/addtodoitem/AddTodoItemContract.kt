package com.pgreen.forgetmenot.addtodoitem

import com.pgreen.forgetmenot.data.GooglePlaceType

interface AddTodoItemContract {

    interface View {
        fun showToast(message: String)
        fun finishActivity()
    }

    interface Presenter {
        fun getGooglePlaceTypes(): Array<GooglePlaceType>
        fun getGooglePlaceUIStringResourceForPosition(position: Int): Int
        fun onGooglePlaceItemChecked(isChecked: Boolean)
        fun onSaveItemButtonClicked()
        fun onCloseMenuButtonClicked()
    }

}