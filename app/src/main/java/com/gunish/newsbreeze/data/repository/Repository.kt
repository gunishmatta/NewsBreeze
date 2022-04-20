package com.gunish.newsbreeze.data.repository

import com.gunish.newsbreeze.data.local.entity.News
import com.gunish.newsbreeze.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNews(): Flow<List<News>>
    suspend fun getNewsById(id: Int): Flow<News>
    suspend fun getSavedNews(): Flow<List<News>>
    suspend fun update(news: News)
}