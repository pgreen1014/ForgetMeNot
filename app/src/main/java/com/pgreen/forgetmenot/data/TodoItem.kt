package com.pgreen.forgetmenot.data

import android.os.Parcel
import android.os.Parcelable
import com.pgreen.forgetmenot.utils.readSetFromParcelTypedList
import java.util.*

data class TodoItem(val name: String,
                    val placeTypes: Set<GooglePlaceType>,
                    val id: String
) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString(), readSetFromParcelTypedList(parcel, GooglePlaceType.CREATOR))

//    private fun readSetFromParcel(parcel: Parcel): Set<GooglePlaceType> {
//        val readPlaceType = mutableListOf<GooglePlaceType>()
//        parcel.readTypedList(readPlaceType, GooglePlaceType.CREATOR)
//        return readPlaceType.toSet()
//    }

    constructor(name: String, placeTypes: Set<GooglePlaceType>): this(name, placeTypes, UUID.randomUUID().toString())

    companion object CREATOR : Parcelable.Creator<TodoItem> {
        override fun createFromParcel(source: Parcel?): TodoItem {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun newArray(size: Int): Array<TodoItem?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeTypedList(placeTypes.toList())
        dest?.writeString(id)
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}