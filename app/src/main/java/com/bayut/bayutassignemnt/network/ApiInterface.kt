package com.katch.hi5.data.api

import com.bayut.bayutassignemnt.model.Images
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("dynamodb-writer")
    fun getImages(): Observable<Response<Images>>
}