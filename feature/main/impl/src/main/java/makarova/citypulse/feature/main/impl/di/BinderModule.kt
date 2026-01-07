package makarova.citypulse.feature.main.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.feature.main.api.repository.CategoryInterestRepository
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.feature.main.api.repository.RecommendationsRepository
import makarova.citypulse.feature.main.api.repository.SearchRepository
import makarova.citypulse.feature.main.api.usecase.ClearCategoryPreferencesUseCase
import makarova.citypulse.feature.main.api.usecase.DetectCityUseCase
import makarova.citypulse.feature.main.api.usecase.GetEventByCategoryUseCase
import makarova.citypulse.feature.main.api.usecase.GetEventCategoriesUseCase
import makarova.citypulse.feature.main.api.usecase.GetRecommendedEventsUseCase
import makarova.citypulse.feature.main.api.usecase.IncreaseCategoryScoreUseCase
import makarova.citypulse.feature.main.api.usecase.SearchEventsUseCase
import makarova.citypulse.feature.main.impl.repository.SearchRepositoryImpl
import makarova.citypulse.feature.main.impl.repository.CategoriesRepositoryIml
import makarova.citypulse.feature.main.impl.repository.CategoryInterestRepositoryImpl
import makarova.citypulse.feature.main.impl.repository.EventsRepositoryImpl
import makarova.citypulse.feature.main.impl.repository.RecommendationsRepositoryImpl
import makarova.citypulse.feature.main.impl.usecasae.ClearCategoryPreferencesUseCaseImpl
import makarova.citypulse.feature.main.impl.usecasae.DetectCityUseCaseImpl
import makarova.citypulse.feature.main.impl.usecasae.GetEventByCategoryUseCaseImpl
import makarova.citypulse.feature.main.impl.usecasae.GetEventCategoriesUseCaseImpl
import makarova.citypulse.feature.main.impl.usecasae.GetRecommendedEventsUseCaseImpl
import makarova.citypulse.feature.main.impl.usecasae.IncreaseCategoryScoreUseCaseImpl
import makarova.citypulse.feature.main.impl.usecasae.SearchEventsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindEventRepository_to_Impl(
        impl: EventsRepositoryImpl
    ): EventsRepository

    @Binds
    @Singleton
    fun bindRecommentRepository_to_Impl(
        impl: RecommendationsRepositoryImpl
    ): RecommendationsRepository

    @Binds
    @Singleton
    fun bindCategoriesRepository_to_Impl(
        impl: CategoriesRepositoryIml
    ): CategoriesRepository

    @Binds
    @Singleton
    fun bindCategoryInterestRepository(
        impl: CategoryInterestRepositoryImpl
    ): CategoryInterestRepository

    @Binds
    @Singleton
    fun bindSearchRepository_to_Impl(
        impl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    @Singleton
    fun bindGetRecommendedEventsUseCase_to_Impl(
        impl: GetRecommendedEventsUseCaseImpl
    ): GetRecommendedEventsUseCase

    @Binds
    @Singleton
    fun bindIncreaseCategoryScoreUseCase_to_Impl(
        impl: IncreaseCategoryScoreUseCaseImpl
    ): IncreaseCategoryScoreUseCase

    @Binds
    @Singleton
    fun bindClearCategoryPreferencesUseCase_to_Impl(
        impl: ClearCategoryPreferencesUseCaseImpl
    ): ClearCategoryPreferencesUseCase

    @Binds
    @Singleton
    fun bindGetCategoryPreferencesUseCase_to_Impl(
        impl: GetEventCategoriesUseCaseImpl
    ): GetEventCategoriesUseCase

    @Binds
    @Singleton
    fun bindGetEventByCategory_to_Impl(
        impl: GetEventByCategoryUseCaseImpl
    ): GetEventByCategoryUseCase

    @Binds
    @Singleton
    fun bindDetectCityUseCase_to_Impl(
        impl: DetectCityUseCaseImpl
    ): DetectCityUseCase

    @Binds
    @Singleton
    fun bindSearchEventsUseCase_to_Impl(
        impl: SearchEventsUseCaseImpl
    ): SearchEventsUseCase

}