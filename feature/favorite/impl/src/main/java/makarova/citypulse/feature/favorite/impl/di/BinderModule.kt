package makarova.citypulse.feature.favorite.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.favorite.api.usecase.*
import makarova.citypulse.feature.favorite.impl.repository.FavoriteEventsRepositoryImpl
import makarova.citypulse.feature.favorite.impl.usecase.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindFavoriteEventsRepository_to_Impl(
        impl: FavoriteEventsRepositoryImpl
    ): FavoriteEventsRepository

    @Binds
    @Singleton
    fun bindGetFavoriteEventsUseCase_to_Impl(
        impl: GetFavoriteEventsUseCaseImpl
    ): GetFavoriteEventsUseCase

    @Binds
    @Singleton
    fun bindGetFavoritesCountUseCase_to_Impl(
        impl: GetFavoritesCountUseCaseImpl
    ): GetFavoritesCountUseCase


    @Binds
    @Singleton
    fun bindIsFavoriteUseCase_to_Impl(
        impl: IsFavoriteUseCaseImpl
    ): IsFavoriteUseCase

    @Binds
    @Singleton
    fun bindToggleFavoriteUseCase_to_Impl(
        impl: ToggleFavoriteUseCaseImpl
    ): ToggleFavoriteUseCase

}