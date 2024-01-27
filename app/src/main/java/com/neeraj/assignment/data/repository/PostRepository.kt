package com.neeraj.assignment.data.repository

import com.neeraj.assignment.data.remote.Resource
import com.neeraj.assignment.data.room.entry.Post

interface PostRepository {

    suspend fun getPostList(): Resource<List<Post>>
}