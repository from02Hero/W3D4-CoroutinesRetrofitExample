package com.example.w3d4_coroutinesretrofitexample.network

import com.google.gson.annotations.SerializedName

data class DataModel(
        @SerializedName("albumId")
        var albumId: kotlin.Int,
        @SerializedName("id")
        var id: kotlin.Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String)