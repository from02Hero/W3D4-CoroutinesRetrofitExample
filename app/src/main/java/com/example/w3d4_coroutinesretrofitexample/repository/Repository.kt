package com.example.w3d4_coroutinesretrofitexample.repository

import com.example.w3d4_coroutinesretrofitexample.network.DataModel
import com.example.w3d4_coroutinesretrofitexample.network.ApiInterface

class Repository(private val apiInterface: ApiInterface) : BaseRepository() {
    //get latest news using safe api call
    suspend fun getData() :  MutableList<DataModel>? {
        return safeApiCall(
            //await the result of deferred type
            call = {apiInterface.getPhotos().await()},
            error = "Error fetching news"
            //convert to mutable list
        )
    }
}