package com.katch.hi5.data.api

import androidx.lifecycle.MutableLiveData

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<T>(
    var successData: T?,
    var errorData: String?
) {



    companion object {


        fun <T> successBody(responseBody: T?): Resource<T> {
            return Resource(responseBody, null)
        }

        fun <T> errorBody(errorBody: String?): Resource<T> {
            return Resource(null, errorBody)
        }
    }
}