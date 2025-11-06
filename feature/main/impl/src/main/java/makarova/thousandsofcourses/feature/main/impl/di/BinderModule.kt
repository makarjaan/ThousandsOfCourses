package makarova.thousandsofcourses.feature.main.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.thousandsofcourses.api.repository.MainRepository
import makarova.thousandsofcourses.feature.main.impl.repository.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindMainRepository_to_Impl(impl: MainRepositoryImpl): MainRepository
}