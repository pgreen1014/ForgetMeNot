package com.pgreen.forgetmenot.baseclasses

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class LoggingSupportFragment: Fragment(), Logger {

    abstract override fun getTAG(): String

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        logD("onAttach() called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD("onCreate() called")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logD("onCreateView() called")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logD("onActivityCreated() called")
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

    override fun onDestroyView() {
        super.onDestroyView()
        logD("onDestroyView() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD("onDestroy() called")
    }

    override fun onDetach() {
        super.onDetach()
        logD("onDetach() called")
    }

}