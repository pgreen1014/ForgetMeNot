package com.pgreen.forgetmenot.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.addtodoitem.AddTodoItemActivity
import com.pgreen.forgetmenot.eventbusevents.OpenAddTodoItemActivityEvent
import com.pgreen.forgetmenot.todolist.TodoItemsListFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.activity_main_fragment_container, TodoItemsListFragment())
                .commit()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun openAddTodoItemActivity(eventOpenEvent: OpenAddTodoItemActivityEvent) {
        val intent = Intent(this, AddTodoItemActivity::class.java)
        startActivity(intent)
    }
}
