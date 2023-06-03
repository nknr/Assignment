package com.neeraj.assignment.data.remote

import com.neeraj.assignment.data.room.entry.Post
import retrofit2.http.GET

interface RestApi {

    @GET("posts")
    suspend fun getPost(): List<Post>
}