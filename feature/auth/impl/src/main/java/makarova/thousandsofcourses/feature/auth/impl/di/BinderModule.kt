package makarova.thousandsofcourses.feature.auth.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.thousandsofcourses.feature.auth.api.repository.AuthRepository
import makarova.thousandsofcourses.feature.auth.impl.repository.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindAuthRepository_to_Impl(impl: AuthRepositoryImpl): AuthRepository
}
