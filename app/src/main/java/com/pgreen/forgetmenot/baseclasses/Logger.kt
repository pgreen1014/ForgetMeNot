package com.pgreen.forgetmenot.baseclasses

import android.util.Log

interface Logger {
    fun getTAG(): String

    fun logD(message: String) {
        Log.d(getTAG(), message)
    }

    fun logE(message: String) {
        Log.e(getTAG(), message)
    }

    fun logV(message: String) {
        Log.v(getTAG(), message)
    }

    fun logI(message: String) {
        Log.i(getTAG(), message)
    }

    fun logW(message: String) {
        Log.w(getTAG(), message)
    }
}