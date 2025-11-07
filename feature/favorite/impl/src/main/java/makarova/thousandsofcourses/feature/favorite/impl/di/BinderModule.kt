package makarova.thousandsofcourses.feature.favorite.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.thousandsofcourses.feature.favorite.api.repository.FavoriteRepository
import makarova.thousandsofcourses.feature.favorite.impl.repository.FavoriteRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindFavoriteRepository_to_Impl(impl: FavoriteRepositoryImpl): FavoriteRepository
}