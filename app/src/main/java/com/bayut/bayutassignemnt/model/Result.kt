package com.bayut.bayutassignemnt.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep

data class Result(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("image_ids")
    val imageIds: List<String>,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("image_urls_thumbnails")
    val imageUrlsThumbnails: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("uid")
    val uid: String
) : Parcelable