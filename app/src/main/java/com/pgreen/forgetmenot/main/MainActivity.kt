package com.pgreen.forgetmenot.main

import android.content.Intent
import android.os.Bundle
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.addtodoitem.AddTodoItemActivity
import com.pgreen.forgetmenot.baseclasses.LoggingAppCompatActivity
import com.pgreen.forgetmenot.eventbusevents.OpenAddTodoItemActivityEvent
import com.pgreen.forgetmenot.todolist.TodoItemsListFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : LoggingAppCompatActivity() {

    override fun getTAG(): String = "MainActivity"

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO don't load fragment if its already loaded
        supportFragmentManager.beginTransaction()
                .add(R.id.activity_main_fragment_container, TodoItemsListFragment())
                .commit()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun openAddTodoItemActivity(event: OpenAddTodoItemActivityEvent) {
        val intent = Intent(this, AddTodoItemActivity::class.java)

        if (event.item != null) {
            intent.putExtra(AddTodoItemActivity.BUNDLE_EDIT_TODO_ITEM, event.item)
        }

        if (event.position != null) {
            intent.putExtra(AddTodoItemActivity.BUNDLE_EDIT_TODO_ITEM_POSITION, event.position)
        }

        startActivity(intent)
    }
}
