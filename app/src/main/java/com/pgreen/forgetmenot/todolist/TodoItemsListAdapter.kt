package com.pgreen.forgetmenot.todolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.data.TodoItem

class TodoItemsListAdapter(
        val presenter: TodoItemsListContract.Presenter,
        var items: List<TodoItem>) : RecyclerView.Adapter<TodoItemsListAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_todoitem, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindViewHolder(position)
    }

    fun refreshData(newItems: List<TodoItem>) {
        items = newItems
        notifyDataSetChanged()
    }


    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemNameTextView: TextView = itemView.findViewById(R.id.item_todoitem_itemName)
        private val itemOptionsButton: ImageView = itemView.findViewById(R.id.item_todoitem_options_button)

        internal fun bindViewHolder(position: Int) {
            itemNameTextView.text = items[position].name
            itemOptionsButton.setOnClickListener { presenter.onItemOptionsClicked(itemOptionsButton, position) }
        }

    }

}
