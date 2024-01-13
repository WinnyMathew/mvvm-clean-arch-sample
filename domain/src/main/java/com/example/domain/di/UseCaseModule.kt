package com.example.domain.di

import com.example.domain.repository.MealRepository
import com.example.domain.use_case.GetCategoriesUseCase
import com.example.domain.use_case.GetMealUseCase
import com.example.domain.use_case.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCategoriesUseCase(repository: MealRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }

    @Provides
    fun provideGetMealsUseCase(repository: MealRepository): GetMealsUseCase {
        return GetMealsUseCase(repository)
    }

    @Provides
    fun provideGetMealUseCase(repository: MealRepository): GetMealUseCase {
        return GetMealUseCase(repository)
    }
}
