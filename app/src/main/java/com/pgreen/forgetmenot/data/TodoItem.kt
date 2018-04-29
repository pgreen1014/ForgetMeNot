package com.pgreen.forgetmenot.data

data class TodoItem(val name: String,
                    val placeTypes: Set<GooglePlaceType>)