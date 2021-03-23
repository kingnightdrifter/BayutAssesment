package com.katch.hi5.data.model.errorResponse


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Error(
    @SerializedName("field")
    val `field`: String,
    @SerializedName("message")
    val message: String?
) : Parcelable