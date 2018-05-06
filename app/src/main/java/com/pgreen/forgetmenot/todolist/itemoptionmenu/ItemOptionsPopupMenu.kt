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
        storage: TodoListStorage,
        private val item: TodoItem
): ItemOptionsPopupMenuContract.View {

    private var popupMenu: PopupMenu? = null
    private val presenter: ItemOptionsPopupMenuContract.Presenter = ItemOptionsPopupMenuPresenter(this, storage)

    override fun showMenu(context: Context, anchor: View) {
        popupMenu = PopupMenu(context, anchor)
        popupMenu?.menuInflater?.inflate(R.menu.todo_item_options, popupMenu?.menu)
        popupMenu?.setOnMenuItemClickListener {
             when (it.itemId) {
                 R.id.todo_item_options_edit    -> presenter.onEditItemOptionClicked(item)
                 R.id.todo_item_options_delete  -> presenter.onDeleteItemOptionClicked(item)
            }
            true
        }

        popupMenu?.show()
    }

    override fun dismissMenu() {
        popupMenu?.dismiss()
    }

    override fun openEditItemView(item: TodoItem) {
        EventBus.getDefault().post(OpenAddTodoItemActivityEvent(item))
    }
}