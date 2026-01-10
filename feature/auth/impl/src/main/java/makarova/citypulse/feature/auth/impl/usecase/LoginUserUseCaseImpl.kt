package makarova.citypulse.feature.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.auth.api.usecase.LoginUseCase
import javax.inject.Inject

class LoginUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
): LoginUseCase {

    override suspend operator fun invoke(email: String, password: String): Boolean {
        return withContext(ioDispatcher) {
            authRepository.login(email, password)
        }
    }
}