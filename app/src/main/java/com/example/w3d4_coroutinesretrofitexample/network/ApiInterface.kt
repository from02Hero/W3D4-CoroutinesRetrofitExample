package com.example.w3d4_coroutinesretrofitexample.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("photos")
    fun getPhotos(): Deferred<Response<MutableList<DataModel>>>
}