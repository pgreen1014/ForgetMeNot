package com.pgreen.forgetmenot.addtodoitem

import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage

class AddTodoItemPresenter(private val view: AddTodoItemContract.View,
                           private val itemStorage: TodoListStorage
) : AddTodoItemContract.Presenter {

    private val checkedGooglePlaceTypes: MutableSet<GooglePlaceType> = mutableSetOf()

    override fun getGooglePlaceTypes(): Array<GooglePlaceType> = GooglePlaceType.values()

    override fun getGooglePlaceUIStringResourceForPosition(position: Int): Int {
        val googlePlaceType = getGooglePlaceTypes()[position]
        return googlePlaceType.getUIPresentationStringID()
    }

    override fun onGooglePlaceItemChecked(placeType: GooglePlaceType, isChecked: Boolean) {
        if (isChecked) {
            checkedGooglePlaceTypes.add(placeType)
        } else {
            checkedGooglePlaceTypes.remove(placeType)
        }
    }

    override fun onSaveItemButtonClicked() {
        val item = TodoItem(view.getItemName(), checkedGooglePlaceTypes)
        itemStorage.saveNewTodoItem(item)
        view.finishActivity()
    }

    override fun onCloseMenuButtonClicked() {
        view.finishActivity()
    }

}