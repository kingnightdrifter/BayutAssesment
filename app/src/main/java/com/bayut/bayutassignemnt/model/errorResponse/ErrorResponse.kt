package com.katch.hi5.data.model.errorResponse

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ErrorResponse(
    @SerializedName("errors")
    val errors: ArrayList<Error> = ArrayList()
) : Parcelable