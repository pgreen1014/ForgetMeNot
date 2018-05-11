package com.pgreen.forgetmenot.utils

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

inline fun <reified T>readSetFromParcelTypedList(parcel: Parcel, creator: Parcelable.Creator<Any>): Set<T> {
    val readList = mutableListOf<Any>()
    parcel.readTypedList(readList, creator)

    val returnList = mutableListOf<T>()
    for (item in readList) {
        if (item is T) {
            returnList.add(item)
        }
    }

    

}