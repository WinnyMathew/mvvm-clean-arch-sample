package com.meals.domain.di

import com.meals.domain.repository.MealRepository
import com.meals.domain.usecase.GetCategoriesUseCase
import com.meals.domain.usecase.GetMealUseCase
import com.meals.domain.usecase.GetMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

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
