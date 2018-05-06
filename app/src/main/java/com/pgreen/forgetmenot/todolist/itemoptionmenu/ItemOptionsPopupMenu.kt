package com.pgreen.forgetmenot.todolist.itemoptionmenu

import android.content.Context
import android.view.View
import android.support.v7.widget.PopupMenu
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.eventbusevents.OpenAddTodoItemActivityEvent
import com.pgreen.forgetmenot.storage.TodoListStorage
import org.greenrobot.eventbus.EventBus

class ItemOptionsPopupMenu(
        private val callback: ItemOptionsPopupMenuCallback,
        private val item: TodoItem
) {

    interface ItemOptionsPopupMenuCallback{
        fun onEditItemPopupMenuOptionClicked(item: TodoItem)
        fun onDeleteItemPopupMenuOptionClicked(item: TodoItem)
    }

    private var popupMenu: PopupMenu? = null

    fun showMenu(context: Context, anchor: View) {
        popupMenu = PopupMenu(context, anchor)
        popupMenu?.menuInflater?.inflate(R.menu.todo_item_options, popupMenu?.menu)
        popupMenu?.setOnMenuItemClickListener {
             when (it.itemId) {
                 R.id.todo_item_options_edit    -> callback.onEditItemPopupMenuOptionClicked(item)
                 R.id.todo_item_options_delete  -> callback.onDeleteItemPopupMenuOptionClicked(item)
            }
            true
        }

        popupMenu?.show()
    }

    fun dismissMenu() {
        popupMenu?.dismiss()
    }
}