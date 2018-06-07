package com.pgreen.forgetmenot.data

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class TodoItem(val name: String,
                    val placeTypes: Set<GooglePlaceType>,
                    val id: String) : Parcelable {

    constructor(name: String, placeTypes: Set<GooglePlaceType>): this(name, placeTypes, UUID.randomUUID().toString())

    companion object CREATOR : Parcelable.Creator<TodoItem>{

        override fun createFromParcel(source: Parcel?): TodoItem? {
            val readName = source?.readString()
            val readSet = readGooglePlaceTypeSetFromParcelList(source)
            val readId = source?.readString()

            return if (readName != null && readId != null) {
                (TodoItem(readName, readSet, readId))
            } else {
                null
            }
        }

        private fun readGooglePlaceTypeSetFromParcelList(parcel: Parcel?): Set<GooglePlaceType> {
            val readList = mutableListOf<GooglePlaceType>()
            parcel?.readList(readList, GooglePlaceType::class.java.classLoader)
            return readList.toSet()
        }

        override fun newArray(size: Int): Array<TodoItem?> {
            return arrayOfNulls(size)
        }

    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeList(placeTypes.toMutableList())
        dest?.writeString(id)
    }

    override fun describeContents(): Int = 0

}
