package com.pgreen.forgetmenot.todolist.itemoptionmenu

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.pgreen.forgetmenot.R

class ItemOptionsPopupMenu {
    private var popupMenu: PopupMenu? = null

    fun showMenu(context: Context, anchor: View) {
        popupMenu = PopupMenu(context, anchor)
        popupMenu?.menuInflater?.inflate(R.menu.todo_item_options, popupMenu?.menu)
        popupMenu?.show()
    }

    fun dismissMenu() {
        popupMenu?.dismiss()
    }

}