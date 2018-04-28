package com.pgreen.forgetmenot.addtodoitem

import com.pgreen.forgetmenot.data.GooglePlaceType

class AddTodoItemPresenter(private val view: AddTodoItemContract.View): AddTodoItemContract.Presenter {

    override fun getGooglePlaceTypes(): Array<GooglePlaceType> = GooglePlaceType.values()

    override fun getGooglePlaceUIStringResourceForPosition(position: Int): Int {
        val googlePlaceType = getGooglePlaceTypes()[position]
        return googlePlaceType.getUIPresentationStringID()
    }

    override fun onGooglePlaceItemChecked(isChecked: Boolean) {
        view.showToast("Item checked: $isChecked")
    }

    override fun onSaveItemButtonClicked() {
        view.finishActivity()
    }

    override fun onCloseMenuButtonClicked() {
        view.finishActivity()
    }

}