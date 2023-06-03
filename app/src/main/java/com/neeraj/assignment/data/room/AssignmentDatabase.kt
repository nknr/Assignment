package com.neeraj.assignment.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neeraj.assignment.data.room.dao.PostDao
import com.neeraj.assignment.data.room.entry.Post
import com.neeraj.assignment.utils.constant.DbConstant

@Database(entities = [Post::class], version = DbConstant.DATABASE_VERSION, exportSchema = true)
abstract class AssignmentDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
}