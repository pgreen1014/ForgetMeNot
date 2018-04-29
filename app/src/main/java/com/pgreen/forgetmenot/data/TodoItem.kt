package com.pgreen.forgetmenot.data

import java.util.*

data class TodoItem(val name: String,
                    val placeTypes: Set<GooglePlaceType>) {
    val id = UUID.randomUUID().toString()
}