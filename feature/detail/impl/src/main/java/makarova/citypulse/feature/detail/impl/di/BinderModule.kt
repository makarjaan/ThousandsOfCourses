package makarova.citypulse.feature.detail.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import makarova.citypulse.feature.detail.api.repository.EventDetailsRepository
import makarova.citypulse.feature.detail.api.usecase.GetEventDetailsUseCase
import makarova.citypulse.feature.detail.impl.repository.EventDetailsRepositoryImpl
import makarova.citypulse.feature.detail.impl.usecase.GetEventDetailsUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindEventRepository_to_Impl(impl: EventDetailsRepositoryImpl): EventDetailsRepository

    @Binds
    @Singleton
    fun bindGetEventDetailsUseCase_to_Impl(impl: GetEventDetailsUseCaseImpl): GetEventDetailsUseCase


}