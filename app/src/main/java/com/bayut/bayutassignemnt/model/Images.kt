package com.bayut.bayutassignemnt.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Images(
    @SerializedName("results")
    val results: List<Result>
) : Parcelable