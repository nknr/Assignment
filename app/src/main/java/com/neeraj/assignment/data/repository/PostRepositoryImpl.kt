package com.neeraj.assignment.data.repository

import com.neeraj.assignment.data.remote.Resource
import com.neeraj.assignment.data.remote.RestApi
import com.neeraj.assignment.data.remote.safeApiCall
import com.neeraj.assignment.data.room.dao.PostDao
import com.neeraj.assignment.data.room.entry.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postDao: PostDao, private val restApi: RestApi) : PostRepository {

    override suspend fun getPost(): Resource<List<Post>> {
        return safeApiCall { restApi.getPost() }
    }
}