package com.neeraj.assignment.di

import android.content.Context
import androidx.room.Room
import com.neeraj.assignment.data.room.AssignmentDatabase
import com.neeraj.assignment.data.room.dao.PostDao
import com.neeraj.assignment.utils.constant.DbConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): AssignmentDatabase {
        return Room.databaseBuilder(context, AssignmentDatabase::class.java, DbConstant.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesPostDao(database: AssignmentDatabase): PostDao {
        return database.postDao()
    }
}