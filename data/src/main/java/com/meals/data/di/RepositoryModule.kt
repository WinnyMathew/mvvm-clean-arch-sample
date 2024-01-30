package com.meals.data.di

import com.meals.data.repository.MealRepositoryImpl
import com.meals.domain.datasource.RemoteDataSource
import com.meals.data.repository.datasourceImpl.RemoteDataSourceImpl
import com.meals.domain.repository.MealRepository
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
