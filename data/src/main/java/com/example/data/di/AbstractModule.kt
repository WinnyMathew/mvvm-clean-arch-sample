package com.example.data.di

import com.example.data.repository.MealRepositoryImpl
import com.example.data.repository.datasource.RemoteDataSource
import com.example.data.repository.datasourceimpl.RemoteDataSourceImpl
import com.example.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: MealRepositoryImpl): MealRepository

    @Binds
    abstract fun bindRemoteDataSource(remoteSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
