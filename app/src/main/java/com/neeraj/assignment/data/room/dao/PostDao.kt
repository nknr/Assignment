package com.neeraj.assignment.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neeraj.assignment.data.room.entry.Post
import com.neeraj.assignment.utils.constant.DbConstant


@Dao
interface PostDao {

    @Query("SELECT * FROM ${DbConstant.Post.TBL_NAME}")
    fun getAll(): List<Post>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBulk(postList: List<Post>): List<Long>

}