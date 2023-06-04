package com.neeraj.assignment.di

import com.neeraj.assignment.data.repository.PostRepository
import com.neeraj.assignment.data.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun getPostRepository(repositoryImpl: PostRepositoryImpl): PostRepository = repositoryImpl
}