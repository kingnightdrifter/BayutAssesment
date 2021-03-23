package com.katch.hi5.data.api

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.katch.hi5.data.model.errorResponse.ErrorResponse
import com.katch.hi5.utils.LogPrint
import com.katch.hi5.utils.Toasty

class ToastMessageErrorView(private val context: Context) : ErrorView {
    private var toastyMsg: Toasty = Toasty(context)
    private val timber = LogPrint(TAG)
    private lateinit var errorResponse: ErrorResponse
    override fun showError(message: String?) {
        if (message != null) {
            timber.d(message)
            try {
                errorResponse = gson.fromJson(message, ErrorResponse::class.java)
                if (errorResponse.errors.size > 0) {
                    toastyMsg.showErrorToasty(errorResponse.errors[0].message!!)
                    timber.d(errorResponse.errors[0].message!!)
                } else {
                    toastyMsg.showInfoToasty("Message object found null BE team please fix")
                }
            } catch (e: JsonParseException) {
                timber.e("Exception: $e")
            }
        }
    }

    override fun TimberLog(tag: String): Any {
        TODO("Not yet implemented")
    }


    companion object {
        private const val TAG = "ToastMessageErrorView"
        val gson = Gson()
    }
}