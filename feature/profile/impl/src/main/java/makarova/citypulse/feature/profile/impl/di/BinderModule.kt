package makarova.citypulse.feature.profile.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.citypulse.feature.profile.api.usecase.*
import makarova.citypulse.feature.profile.impl.usecase.*

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    fun bindUpdateUserNameUseCase(
        impl: UpdateUserNameUseCaseImpl
    ): UpdateUserNameUseCase

    @Binds
    fun bindChangePasswordUseCase(
        impl: ChangePasswordUseCaseImpl
    ): ChangePasswordUseCase

    @Binds
    fun bindGetTopCategoriesUseCase(
        impl: GetTopCategoriesUseCaseImpl
    ): GetTopCategoriesUseCase
}