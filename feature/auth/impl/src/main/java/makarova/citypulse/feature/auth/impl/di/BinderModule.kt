package makarova.citypulse.feature.auth.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.auth.api.usecase.CheckAuthUseCase
import makarova.citypulse.feature.auth.api.usecase.GetCurrentUserUseCase
import makarova.citypulse.feature.auth.api.usecase.LoginUseCase
import makarova.citypulse.feature.auth.api.usecase.LogoutUseCase
import makarova.citypulse.feature.auth.api.usecase.RegisterUseCase
import makarova.citypulse.feature.auth.impl.repository.AuthRepositoryImpl
import makarova.citypulse.feature.auth.impl.usecase.CheckAuthUseCaseImpl
import makarova.citypulse.feature.auth.impl.usecase.GetCurrentUserUseCaseImpl
import makarova.citypulse.feature.auth.impl.usecase.LoginUserUseCaseImpl
import makarova.citypulse.feature.auth.impl.usecase.LogoutUseCaseImpl
import makarova.citypulse.feature.auth.impl.usecase.RegisterUserUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindAuthRepository_to_Impl(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindCheckAuthUseCase_to_Impl(impl: CheckAuthUseCaseImpl): CheckAuthUseCase

    @Binds
    @Singleton
    fun bindLoginUserUseCase_to_Impl(impl: LoginUserUseCaseImpl): LoginUseCase

    @Binds
    @Singleton
    fun bindRegisterUserUseCase_to_Impl(impl: RegisterUserUseCaseImpl): RegisterUseCase

    @Binds
    @Singleton
    fun bindGetCurrentUserUseCase_to_Impl(impl: GetCurrentUserUseCaseImpl): GetCurrentUserUseCase

    @Binds
    @Singleton
    fun bindLogOutUseCase_to_Impl(impl: LogoutUseCaseImpl): LogoutUseCase
}
