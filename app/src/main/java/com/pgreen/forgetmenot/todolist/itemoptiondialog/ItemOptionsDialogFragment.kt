package com.pgreen.forgetmenot.todolist.itemoptiondialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.pgreen.forgetmenot.R

class ItemOptionsDialogFragment() : DialogFragment() {

    private var callback: ItemOptionsDialogCallback? = null

    interface ItemOptionsDialogCallback {
        fun editItemDialogOptionClicked()
        fun deleteItemDialogOptionClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            callback = targetFragment as ItemOptionsDialogCallback
        } catch (e: ClassCastException) {
            throw ClassCastException("${context.toString()} must implement ItemOptionsDialogFragment.ItemOptionsDialogCallback")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //TODO figure out better way to construct without using !! operator
        val builder = AlertDialog.Builder(context!!)
        builder.setItems(R.array.item_list_options, DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                0 -> callback?.editItemDialogOptionClicked()
                1 -> callback?.deleteItemDialogOptionClicked()
            }
        })

        return builder.create()
    }

}