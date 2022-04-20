package com.gunish.newsbreeze.data.remote

import com.gunish.newsbreeze.data.model.NewsResponse
import com.gunish.newsbreeze.utils.CommonUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("v2/top-headlines?country=in")
    suspend fun fetchNewsData(@Query("apiKey") key :String = CommonUtils.APIKEY):NewsResponse
    companion object {
        const val API_URL = "https://newsapi.org/"
    }

}