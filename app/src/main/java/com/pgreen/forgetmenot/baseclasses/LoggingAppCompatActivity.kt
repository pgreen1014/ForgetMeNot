package com.pgreen.forgetmenot.baseclasses

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class LoggingAppCompatActivity: AppCompatActivity(), Logger {

    abstract override fun getTAG(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD("onCreate() called")
    }

    override fun onStart() {
        super.onStart()
        logD("onStart() called")
    }

    override fun onResume() {
        super.onResume()
        logD("onResume() called")
    }

    override fun onPause() {
        super.onPause()
        logD("onPause() called")
    }

    override fun onStop() {
        super.onStop()
        logD("onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD("onDestroy() called")
    }

}