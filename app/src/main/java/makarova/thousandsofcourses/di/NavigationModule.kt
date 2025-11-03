package makarova.thousandsofcourses.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import makarova.thousandsofcourses.feature.auth.impl.navigation.AuthScreenRegistrationImpl
import makarova.thousandsofcourses.navigation.ScreenRegistration

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @IntoSet
    fun provideAuthRegistration(): ScreenRegistration = AuthScreenRegistrationImpl()
}