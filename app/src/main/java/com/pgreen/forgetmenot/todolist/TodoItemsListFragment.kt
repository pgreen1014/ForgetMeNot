package com.pgreen.forgetmenot.todolist

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.eventbusevents.AddTodoItemButtonUIClickEvent

import kotlinx.android.synthetic.main.fragment_todo_items_list.*
import org.greenrobot.eventbus.EventBus

class TodoItemsListFragment : Fragment(), TodoItemsListContract.View {

    private val presenter: TodoItemsListContract.Presenter = TodoItemsListPresenter(this)

    private lateinit var itemsListRecyclerView: RecyclerView
    private lateinit var addItemFAB: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_todo_items_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        initRecyclerView()
        addItemFAB.setOnClickListener { presenter.onAddTodoItemsClicked() }
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

    override fun updateTodoItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun launchAddTodoItemsActivity() {
        EventBus.getDefault().post(AddTodoItemButtonUIClickEvent())
    }

}