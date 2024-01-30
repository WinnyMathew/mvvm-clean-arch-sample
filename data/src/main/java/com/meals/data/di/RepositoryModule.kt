package com.meals.data.di

import com.meals.data.repository.MealRepositoryIMPL
import com.meals.domain.datasource.RemoteDataSource
import com.meals.data.repository.datasourceImpl.RemoteDataSourceIMPL
import com.meals.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: MealRepositoryIMPL): MealRepository

    @Binds
    abstract fun bindRemoteDataSource(remoteSourceImpl: RemoteDataSourceIMPL): RemoteDataSource
}
