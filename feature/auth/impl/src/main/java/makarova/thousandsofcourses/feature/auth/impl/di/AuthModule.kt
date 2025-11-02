package makarova.thousandsofcourses.feature.auth.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import makarova.thousandsofcourses.feature.auth.api.repository.AuthRepository
import makarova.thousandsofcourses.feature.auth.impl.navigation.AuthScreenRegistrationImpl
import makarova.thousandsofcourses.feature.auth.impl.repository.AuthRepositoryImpl
import makarova.thousandsofcourses.navigation.ScreenRegistration

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @IntoSet
    fun provideAuthRegistration(): ScreenRegistration = AuthScreenRegistrationImpl()

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()
}