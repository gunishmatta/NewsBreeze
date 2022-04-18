package com.gunish.newsbreeze.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*
import retrofit2.Response

/*
A repository which will provide resource from local db as well as remote
 */

abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {
        emit(Resource.Success(fetchFromLocal().first()))

        val apiResponse=fetchFromRemote()

        val remoteArticles=apiResponse.body()

        if(apiResponse.isSuccessful&&remoteArticles!=null)
        {
            saveRemoteData(remoteArticles)
        }
        else{
            emit(Resource.Failed(apiResponse.message()))
        }

        emitAll(
            fetchFromLocal().map {
                Resource.Success<RESULT>(it)
            }
        )
    }.catch {
        e-> e.printStackTrace()
        emit(Resource.Failed("Network Error.."))
    }

    /*
    Save remote data into persistent storage
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(request: REQUEST)

    /*
    Fetch from local persistent storage
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    /*
    Fetches response from remote end point
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>

}