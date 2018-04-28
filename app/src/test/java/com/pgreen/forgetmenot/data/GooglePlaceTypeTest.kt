package com.pgreen.forgetmenot.data

import com.pgreen.forgetmenot.R
import org.junit.Assert
import org.junit.Test

class GooglePlaceTypeTest {

    @Test
    fun testAPIString() {
        val type = GooglePlaceType.ATM
        val apiString = type.getApiString()
        Assert.assertTrue("GooglePlaceType: $apiString apiString is incorrect", apiString == "atm")
    }

    @Test
    fun testUIPresentationString() {
        val type = GooglePlaceType.ATM
        val presentationStringID = type.getUIPresentationStringID()
        val resourceID = R.string.google_place_atm
        Assert.assertTrue("Attempting to access the wrong resource id", presentationStringID == resourceID)
    }

    @Test
    fun testAllAPIString() {
        for (placeType in GooglePlaceType.values()) {

            val toString = placeType.toString()
            val lowercasedString = toString.toLowerCase()
            val apiString = placeType.getApiString()

            Assert.assertTrue("$apiString does not match $lowercasedString", lowercasedString == apiString)
        }
    }

    @Test
    fun uiStringDoesNotEqual0() {
        for (placeType in GooglePlaceType.values()) {

            val id = placeType.getUIPresentationStringID()

            Assert.assertTrue("GooglePlaceType: " + placeType.toString() + " needs to be assigned a string resource", id != 0)

        }
    }

    @Test
    fun notUsingSameResourceIDTwice() {

        for (placeType in GooglePlaceType.values()) {

            val id = placeType.getUIPresentationStringID()

            Assert.assertFalse(placeType.toString() + " is used multiple times", isIdUsedMoreThanOnce(id))

        }

    }

    @Test
    fun insertsUnderscoreInPlaceOfSpace() {
        val placeType = GooglePlaceType.BEAUTY_SALON
        val apiStirng = placeType.getApiString()
        Assert.assertTrue("Spaces should be replaced by _", apiStirng == "beauty_salon")
    }

    private fun isIdUsedMoreThanOnce(id: Int): Boolean {
        var useCount = 0
        for (placeType in GooglePlaceType.values()) {

            val idToTestAgainst = placeType.getUIPresentationStringID()

            if (id == idToTestAgainst) {
                useCount += 1
            }

        }

        return useCount > 1
    }

}