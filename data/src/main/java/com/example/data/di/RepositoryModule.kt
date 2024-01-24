package com.example.data.di

import com.example.data.repository.MealRepositoryImpl
import com.example.domain.datasource.RemoteDataSource
import com.example.data.repository.datasourceImpl.RemoteDataSourceImpl
import com.example.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: MealRepositoryImpl): MealRepository

    @Binds
    abstract fun bindRemoteDataSource(remoteSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
