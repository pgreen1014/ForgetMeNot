package com.pgreen.forgetmenot.addtodoitem

import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoListStorage

class AddTodoItemPresenter(private val view: AddTodoItemContract.View,
                           private val itemStorage: TodoListStorage
) : AddTodoItemContract.Presenter {

    private var editItem: TodoItem? = null
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
        val itemName = view.getItemName()

        if (editItem != null) {
            val item = TodoItem(itemName, checkedGooglePlaceTypes, editItem?.id!!)
            itemStorage.updateTodoItem(item)
        } else {
            val item = TodoItem(itemName, checkedGooglePlaceTypes)
            itemStorage.saveNewTodoItem(item)
        }

        view.finishActivity()
    }

    override fun onCloseMenuButtonClicked() {
        view.finishActivity()
    }

    override fun shouldGooglePlaceTypeBeCheckedForItemEditing(position: Int, editItemPlaceTypes: Set<GooglePlaceType>): Boolean {
        val placeType = getGooglePlaceTypes()[position]

        //TODO Unit Test this
        val shouldCheck = editItemPlaceTypes.contains(placeType)
        if (shouldCheck) {
            checkedGooglePlaceTypes.add(placeType)
        }

        return shouldCheck
    }

    override fun setItemToEdit(editItem: TodoItem?) {
        this.editItem = editItem

        if (editItem != null) {
            view.setItemName(editItem.name)
        }
    }

    override fun getGooglePlaceTypesForEditingItem(): Set<GooglePlaceType>? {
        return editItem?.placeTypes
    }
}