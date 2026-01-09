package makarova.citypulse.feature.profile.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.citypulse.feature.profile.api.usecase.ChangePasswordUseCase
import makarova.citypulse.feature.profile.api.usecase.UpdateUserAvatarUseCase
import makarova.citypulse.feature.profile.api.usecase.UpdateUserNameUseCase
import makarova.citypulse.feature.profile.impl.usecase.ChangePasswordUseCaseImpl
import makarova.citypulse.feature.profile.impl.usecase.UpdateUserAvatarUseCaseImpl
import makarova.citypulse.feature.profile.impl.usecase.UpdateUserNameUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    fun bindUpdateUserNameUseCase(
        impl: UpdateUserNameUseCaseImpl
    ): UpdateUserNameUseCase

    @Binds
    fun bindUpdateUserAvatarUseCase(
        impl: UpdateUserAvatarUseCaseImpl
    ): UpdateUserAvatarUseCase

    @Binds
    fun bindChangePasswordUseCase(
        impl: ChangePasswordUseCaseImpl
    ): ChangePasswordUseCase
}