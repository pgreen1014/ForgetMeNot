package com.pgreen.forgetmenot.todolist

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.baseclasses.LoggingSupportFragment
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.eventbusevents.OpenAddTodoItemActivityEvent
import com.pgreen.forgetmenot.eventbusevents.storage.UpdateItemEvent
import com.pgreen.forgetmenot.storage.TodoListStorageObject
import com.pgreen.forgetmenot.todolist.itemoptionmenu.ItemOptionsPopupMenu

import kotlinx.android.synthetic.main.fragment_todo_items_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class TodoItemsListFragment : LoggingSupportFragment(), TodoItemsListContract.View, ItemOptionsPopupMenu.ItemOptionsPopupMenuCallback {

    private val presenter: TodoItemsListContract.Presenter = TodoItemsListPresenter(this, TodoListStorageObject)

    private lateinit var itemsListRecyclerView: RecyclerView
    private lateinit var addItemFAB: FloatingActionButton

    override fun getTAG(): String = "TodoItemsListFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_todo_items_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        initRecyclerView()
        addItemFAB.setOnClickListener { presenter.onAddTodoItemsClicked() }
    }

    override fun onResume() {
        super.onResume()
        presenter.checkForUpdatedItems()
    }

    private fun initViews() {
        itemsListRecyclerView = fragment_todo_items_list_RecyclerView
        addItemFAB = fragment_todo_items_list_FAB
    }

    private fun initRecyclerView() {
        itemsListRecyclerView.layoutManager = LinearLayoutManager(activity)
        val todoItemsListAdapter = TodoItemsListAdapter(presenter)
        itemsListRecyclerView.adapter = todoItemsListAdapter
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showItemOptionsDialog(itemView: View, item: TodoItem, position: Int) {
        val popupMenu = ItemOptionsPopupMenu(this, item, position)
        popupMenu.showMenu(context!!, itemView)
    }

    override fun launchAddTodoItemsActivity(editItem: TodoItem?, position: Int?) {
        val event = if (editItem != null) OpenAddTodoItemActivityEvent(editItem, position) else OpenAddTodoItemActivityEvent(null, null)

        EventBus.getDefault().post(event)
    }

    override fun updateTodoList() {
        itemsListRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun onDeleteItemPopupMenuOptionClicked(item: TodoItem, position: Int) {
        presenter.onDeleteItemClicked(item)
    }

    override fun onEditItemPopupMenuOptionClicked(item: TodoItem, position: Int) {
        presenter.onEditItemClicked(item, position)
    }

    override fun updateListForPosition(position: Int) {
        itemsListRecyclerView.adapter.notifyItemChanged(position)
    }

    @Subscribe
    fun onUpdateItemEvent(event: UpdateItemEvent) {
        presenter.storePositionOfUpdatedItem(event.itemPosition)
    }

}