package makarova.citypulse.feature.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.auth.api.usecase.LogoutUseCase
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
): LogoutUseCase {

    override suspend operator fun invoke() {
        return withContext(ioDispatcher) {
            authRepository.logout()
        }
    }
}