package com.pgreen.forgetmenot.addtodoitem

import com.pgreen.forgetmenot.data.GooglePlaceType
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.storage.TodoItemsDataSource

class AddTodoItemPresenter(private val view: AddTodoItemContract.View,
                           private val itemsDataSource: TodoItemsDataSource
) : AddTodoItemContract.Presenter {

    private var editItem: TodoItem? = null
    private var editItemPosition: Int? = null
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

            itemsDataSource.updateItem(item, object : TodoItemsDataSource.ItemUpdatedCallback {
                override fun onItemUpdated(item: TodoItem) {

                }

                override fun onItemUnavailable() {

                }
            })

        } else {
            val item = TodoItem(itemName, checkedGooglePlaceTypes)
            itemsDataSource.addTodoItem(item, object : TodoItemsDataSource.AddItemCallback {
                override fun onItemAdded(addedItem: TodoItem) {

                }

                override fun onError() {

                }
            })
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

    override fun setItemToEdit(editItem: TodoItem?, editItemPosition: Int?) {
        this.editItem = editItem
        this.editItemPosition = editItemPosition

        if (editItem != null) {
            view.setItemName(editItem.name)
        }
    }

    override fun getGooglePlaceTypesForEditingItem(): Set<GooglePlaceType>? {
        return editItem?.placeTypes
    }
}