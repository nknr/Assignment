package com.neeraj.assignment.data.repository

import com.neeraj.assignment.data.remote.Resource
import com.neeraj.assignment.data.remote.RestApi
import com.neeraj.assignment.data.room.dao.PostDao
import com.neeraj.assignment.data.room.entry.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postDao: PostDao, private val restApi: RestApi) : PostRepository {

    override suspend fun getPostList(): Resource<List<Post>> {
        return withContext(Dispatchers.IO) {
            val localPostList = postDao.getAll()
            if (localPostList.isNotEmpty()) {
                return@withContext Resource.Success(localPostList)
            } else {
                try {
                    postDao.insertBulk(restApi.getPost())
                    return@withContext Resource.Success(postDao.getAll())
                } catch (exception: Exception) {
                    Resource.Failure(exception)
                }
            }
        }
    }
}