package com.gunish.newsbreeze.data.repository

import javax.inject.Inject

class RepositoryImp @Inject constructor():Repository {
    override suspend fun getNews() {

    }

    override suspend fun getNewsById(id: Int) {
    }

    override suspend fun getSavedNews() {
    }
}