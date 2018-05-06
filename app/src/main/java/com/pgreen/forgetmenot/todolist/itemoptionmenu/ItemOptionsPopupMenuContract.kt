package com.pgreen.forgetmenot.todolist.itemoptionmenu

import android.content.Context
import com.pgreen.forgetmenot.data.TodoItem

interface ItemOptionsPopupMenuContract {

    interface View {
        fun showMenu(context: Context, anchor: android.view.View)
        fun dismissMenu()
        fun openEditItemView(item: TodoItem)
    }

    interface Presenter {
        fun onEditItemOptionClicked(item: TodoItem)
        fun onDeleteItemOptionClicked(item: TodoItem)
    }
}