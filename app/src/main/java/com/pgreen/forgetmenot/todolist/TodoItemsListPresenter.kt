package com.pgreen.forgetmenot.todolist

class TodoItemsListPresenter(val view: TodoItemsListContract.View) : TodoItemsListContract.Presenter {

    override fun onAddTodoItemsClicked() {
        view.launchAddTodoItemsActivity()
    }

    override fun onItemOptionsClicked(itemPosition: Int) {
        view.showToast("Item $itemPosition clicked!")
    }

    override fun onEditItemClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDeleteItemClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadTodoItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}