package com.gunish.newsbreeze.data.repository

import com.gunish.newsbreeze.data.local.dao.NewsDao
import com.gunish.newsbreeze.data.local.entity.News
import com.gunish.newsbreeze.data.remote.RemoteApiService
import com.gunish.newsbreeze.utils.CommonUtils
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiService: RemoteApiService,
    private val newsDao: NewsDao
) : Repository {

    val data = newsDao.getAllNews()
    private val localNewsList: ArrayList<News> = ArrayList()

    override suspend fun getNews(): Flow<List<News>> {
        refreshNews().collect { newsList->
            if(newsList.isNotEmpty())
            {
                newsDao.deleteAll()
                newsDao.insertList(newsList)
            }
        }
        return newsDao.getAllNews()
    }

    override suspend fun getNewsById(id: Int): Flow<News> = newsDao.getNewsById(id)


    override suspend fun getSavedNews(): Flow<List<News>> = newsDao.getAllSavedNews()


    override suspend fun update(news: News)
    {
        newsDao.update(news = news)
    }

    private fun refreshNews(): Flow<List<News>> {
        return flow {
            if (CommonUtils.isConnected()) {
                val response = apiService.fetchNewsData()
                val list: ArrayList<News> = ArrayList()
                localNewsList.addAll(data.first())
                if (response.status.equals("ok")) {
                    if (response.articles!!.isNotEmpty()) {
                        for (article in response.articles) {
                            var isAlreadyInList = false
                            var savedStatus = 0
                            if (localNewsList.isNotEmpty()) {
                                for (localNewsArticle in localNewsList) {
                                    if (localNewsArticle.Title == article.title) {
                                        savedStatus = localNewsArticle.Saved
                                        isAlreadyInList = true
                                        break
                                    }
                                }
                            }
                            if (!isAlreadyInList) {
                                list.add(
                                    News(
                                        article.title,
                                        article.description!!,
                                        article.author!!,
                                        article.urlToImage.toString(),
                                        article.url!!,
                                        article.publishedAt!!,
                                        article.content!!,
                                        article.source.name,
                                        0
                                    )
                                )
                            } else {
                                list.add(
                                    News(
                                        article.title,
                                        article.description!!,
                                        article.author!!,
                                        article.urlToImage.toString(),
                                        article.url!!,
                                        article.publishedAt!!,
                                        article.content!!,
                                        article.source.name,
                                        savedStatus
                                    )
                                )
                            }
                        }
                    }

                }
                emit(list)
            } else {
                emit(newsDao.getAllNews().first())
            }
        }.flowOn(Dispatchers.IO)
    }


}