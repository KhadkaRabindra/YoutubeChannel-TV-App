package com.borkor.shobizandoid.di

import com.borkor.shobizandoid.data.repository.YouTubeRepository
import com.borkor.shobizandoid.data.repository.YouTubeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(youTubeRepositoryImpl: YouTubeRepositoryImpl): YouTubeRepository
}