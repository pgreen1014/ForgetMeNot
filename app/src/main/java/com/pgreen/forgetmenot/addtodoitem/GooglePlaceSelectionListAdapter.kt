package com.pgreen.forgetmenot.addtodoitem

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.pgreen.forgetmenot.R
import com.pgreen.forgetmenot.interfaces.ResourceProvider

class GooglePlaceSelectionListAdapter(private val resourceProvider: ResourceProvider,
                                      private val presenter: AddTodoItemContract.Presenter
) : RecyclerView.Adapter<GooglePlaceSelectionListAdapter.ItemHolder>() {

    private val googlePlaceTypeList = presenter.getGooglePlaceTypes()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_google_place, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int = googlePlaceTypeList.size


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindViewHolder(position)
    }



    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemNameTextView: TextView = itemView.findViewById(R.id.item_google_place_TextView)
        private val selectionCheckBox: CheckBox = itemView.findViewById(R.id.item_google_place_selection_checkBox)

        internal fun bindViewHolder(position: Int) {
            val itemNameResId = presenter.getGooglePlaceUIStringResourceForPosition(position)
            itemNameTextView.text = resourceProvider.getStringResource(itemNameResId)

            selectionCheckBox.setOnCheckedChangeListener { buttonView, isChecked -> presenter.onGooglePlaceItemChecked(isChecked) }
        }
    }
}