package com.pgreen.forgetmenot.addtodoitem

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.baseclasses.LoggingAppCompatActivity
import com.pgreen.forgetmenot.data.TodoItem
import com.pgreen.forgetmenot.eventbusevents.storage.UpdateItemEvent
import com.pgreen.forgetmenot.interfaces.ResourceProvider
import com.pgreen.forgetmenot.storage.TodoListStorage
import com.pgreen.forgetmenot.storage.TodoListStorageObject
import kotlinx.android.synthetic.main.activity_add_todo_item.*
import org.greenrobot.eventbus.EventBus

class AddTodoItemActivity : LoggingAppCompatActivity(), AddTodoItemContract.View, ResourceProvider {

    private val storage: TodoListStorage = TodoListStorageObject
    private val presenter: AddTodoItemContract.Presenter = AddTodoItemPresenter(this, storage)
    private lateinit var googlePlaceItemsRecyclerView: RecyclerView

    companion object {
        const val BUNDLE_EDIT_TODO_ITEM = "com.pgreen.forgetmenot.addtodoitem.AddTodoItemActivity.edit_todo_item"
        const val BUNDLE_EDIT_TODO_ITEM_POSITION = "com.pgreen.forgetmenot.addtodoitem.AddTodoItemActivity.edit_todo_item_position"
    }

    override fun getTAG(): String = "AddTodoItemActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo_item)
        extractBundleData()
        initToolbar()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        googlePlaceItemsRecyclerView = add_todo_item_google_place_selection_recyclerView
        googlePlaceItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        googlePlaceItemsRecyclerView.adapter = GooglePlaceSelectionListAdapter(this, presenter, presenter.getGooglePlaceTypesForEditingItem())
    }

    private fun extractBundleData() {
        if (intent.extras != null) {
            val editItem: TodoItem = intent.extras.getParcelable(BUNDLE_EDIT_TODO_ITEM)
            val editItemPosition: Int = intent.extras.getInt(BUNDLE_EDIT_TODO_ITEM_POSITION)
            presenter.setItemToEdit(editItem, editItemPosition)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_todo_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_item -> {
                presenter.onSaveItemButtonClicked()
                true
            }

            android.R.id.home -> {
                presenter.onCloseMenuButtonClicked()
                true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = add_todo_item_toolbar
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun finishActivity() {
        finish()
    }

    override fun getStringResource(resourceId: Int): String = getString(resourceId)

    override fun getItemName(): String = add_todo_item_itemName_TextInputEditText.text.toString()

    override fun setItemName(itemName: String) {
        add_todo_item_itemName_TextInputEditText.setText(itemName, TextView.BufferType.EDITABLE)
    }

    override fun postUpdateItemEvent(editItemPosition: Int) {
        EventBus.getDefault().post(UpdateItemEvent(editItemPosition))
    }

}
