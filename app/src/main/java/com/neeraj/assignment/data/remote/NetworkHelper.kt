package com.neeraj.assignment.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


sealed class Resource<out T : Any>() {
    class Failure<T : Any>(val throwable: Exception) : Resource<T>()
    class Success<T : Any>(val data: T) : Resource<T>()
}


suspend fun <T : Any> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): Resource<T> {
    return withContext(dispatcher) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}


