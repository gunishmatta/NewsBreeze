package com.gunish.newsbreeze.data.repository

interface Repository {
    suspend fun getNews()
    suspend fun getNewsById(id: Int)
    suspend fun getSavedNews()

}