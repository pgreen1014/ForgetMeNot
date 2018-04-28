package com.pgreen.forgetmenot.interfaces

/**
 * Abstraction for providing application resources to business logic classes
 */
interface ResourceProvider {
    fun getStringResource(resourceId: Int): String
}