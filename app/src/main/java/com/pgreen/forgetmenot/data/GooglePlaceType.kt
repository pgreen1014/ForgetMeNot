package com.pgreen.forgetmenot.data

import android.os.Parcel
import android.os.Parcelable
import com.pgreen.forgetmenot.R

/**
 * Enum class representing non-deprecated Google Place Types that can be used in searching the Google Places API.
 * This Enum is necessary because I could not find an api to get this data. This data will need to be maintained as the
 * list changes.
 */
enum class GooglePlaceType: Parcelable {
    ATM {
        override fun getUIPresentationStringID(): Int = R.string.google_place_atm
    },

    BAKERY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_bakery
    },

    BANK {
        override fun getUIPresentationStringID(): Int = R.string.google_place_bank
    },

    BEAUTY_SALON{
        override fun getUIPresentationStringID(): Int = R.string.google_place_beauty_salon
    },

    BICYCLE_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_bicycle_store
    },

    BOOK_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_book_store
    },

    CAFE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_cafe
    },

    CAR_DEALER {
        override fun getUIPresentationStringID(): Int = R.string.google_place_car_dealer
    },

    CAR_RENTAL {
        override fun getUIPresentationStringID(): Int = R.string.google_place_car_rental
    },

    CAR_REPAIR {
        override fun getUIPresentationStringID(): Int = R.string.google_place_car_repair
    },

    CAR_WASH {
        override fun getUIPresentationStringID(): Int = R.string.google_place_car_wash
    },

    CHURCH {
        override fun getUIPresentationStringID(): Int = R.string.google_place_church
    },

    CITY_HALL {
        override fun getUIPresentationStringID(): Int = R.string.google_place_city_hall
    },

    CLOTHING_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_clothing_store
    },

    CONVENIENCE_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_convenience_store
    },

    COURTHOUSE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_courthouse
    },

    DENTIST {
        override fun getUIPresentationStringID(): Int = R.string.google_place_dentist
    },

    DEPARTMENT_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_department_store
    },

    DOCTOR {
        override fun getUIPresentationStringID(): Int = R.string.google_place_doctor
    },

    ELECTRICIAN {
        override fun getUIPresentationStringID(): Int = R.string.google_place_electrician
    },

    ELECTRONICS_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_electronics_store
    },

    EMBASSY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_embassy
    },

    FLORIST {
        override fun getUIPresentationStringID(): Int = R.string.google_place_florist
    },

    FUNERAL_HOME {
        override fun getUIPresentationStringID(): Int = R.string.google_place_funeral_home
    },

    FURNITURE_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_furniture_store
    },

    GAS_STATION {
        override fun getUIPresentationStringID(): Int = R.string.google_place_gas_station
    },

    GYM {
        override fun getUIPresentationStringID(): Int = R.string.google_place_gym
    },

    HAIR_CARE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_hair_care
    },

    HARDWARE_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_hardware_store
    },

    HINDU_TEMPLE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_hindu_temple
    },

    HOME_GOODS_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_home_goods_store
    },

    HOSPITAL {
        override fun getUIPresentationStringID(): Int = R.string.google_place_hospital
    },

    INSURANCE_AGENCY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_insurance_agency
    },

    JEWELRY_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_jewelry_store
    },

    LAUNDRY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_laundry
    },

    LAWYER {
        override fun getUIPresentationStringID(): Int = R.string.google_place_lawyer
    },

    LIBRARY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_library
    },

    LIQUOR_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_liquor_store
    },

    LOCAL_GOVERNMENT_OFFICE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_local_government_office
    },

    LOCKSMITH {
        override fun getUIPresentationStringID(): Int = R.string.google_place_locksmith
    },

    LODGING {
        override fun getUIPresentationStringID(): Int = R.string.google_place_lodging
    },

    MEAL_DELIVERY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_meal_delivery
    },

    MEAL_TAKEAWAY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_meal_takeaway
    },

    MOSQUE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_mosque
    },

    MOVIE_RENTAL {
        override fun getUIPresentationStringID(): Int = R.string.google_place_movie_rental
    },

    MOVIE_THEATER {
        override fun getUIPresentationStringID(): Int = R.string.google_place_movie_theater
    },

    MOVING_COMPANY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_moving_company
    },

    MUSEUM {
        override fun getUIPresentationStringID(): Int = R.string.google_place_museum
    },

    PAINTER {
        override fun getUIPresentationStringID(): Int = R.string.google_place_painter
    },

    PARK {
        override fun getUIPresentationStringID(): Int = R.string.google_place_park
    },

    PET_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_pet_store
    },

    PHARMACY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_pharmacy
    },

    PHYSIOTHERAPIST {
        override fun getUIPresentationStringID(): Int = R.string.google_place_physiotherapist
    },

    PLUMBER {
        override fun getUIPresentationStringID(): Int = R.string.google_place_plumber
    },

    POLICE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_police
    },

    POST_OFFICE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_post_office
    },

    REAL_ESTATE_AGENCY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_real_estate_agency
    },

    RESTAURANT {
        override fun getUIPresentationStringID(): Int = R.string.google_place_restaurant
    },

    ROOFING_CONTRACTOR {
        override fun getUIPresentationStringID(): Int = R.string.google_place_roofing_contractor
    },

    RV_PARK {
        override fun getUIPresentationStringID(): Int = R.string.google_place_rv_park
    },

    SCHOOL {
        override fun getUIPresentationStringID(): Int = R.string.google_place_school
    },

    SHOE_STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_shoe_store
    },

    SHOPPING_MALL {
        override fun getUIPresentationStringID(): Int = R.string.google_place_shopping_mall
    },

    SPA {
        override fun getUIPresentationStringID(): Int = R.string.google_place_spa
    },

    STADIUM {
        override fun getUIPresentationStringID(): Int = R.string.google_place_stadium
    },

    STORAGE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_storage
    },

    STORE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_store
    },

    SUBWAY_STATION {
        override fun getUIPresentationStringID(): Int = R.string.google_place_subway_station
    },

    SYNAGOGUE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_synagogue
    },

    TAXI_STAND {
        override fun getUIPresentationStringID(): Int = R.string.google_place_taxi_stand
    },

    TRAIN_STATION {
        override fun getUIPresentationStringID(): Int = R.string.google_place_train_station
    },

    TRANSIT_STATION {
        override fun getUIPresentationStringID(): Int = R.string.google_place_transit_station
    },

    TRAVEL_AGENCY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_travel_agency
    },

    UNIVERSITY {
        override fun getUIPresentationStringID(): Int = R.string.google_place_university
    },

    VETERINARY_CARE {
        override fun getUIPresentationStringID(): Int = R.string.google_place_veterinary_care
    },

    ZOO {
        override fun getUIPresentationStringID(): Int = R.string.google_place_zoo
    };

    abstract fun getUIPresentationStringID(): Int
    fun getApiString(): String = this.toString().toLowerCase()



    companion object CREATOR : Parcelable.Creator<GooglePlaceType> {
        override fun createFromParcel(source: Parcel): GooglePlaceType {
            return GooglePlaceType.values()[source.readInt()]
        }

        override fun newArray(size: Int): Array<GooglePlaceType?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }
}