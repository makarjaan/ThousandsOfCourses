package makarova.citypulse.feature.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.auth.api.usecase.RegisterUseCase
import javax.inject.Inject

class RegisterUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) : RegisterUseCase {

    override suspend operator fun invoke(email: String, name: String, password: String): Boolean {
        return withContext (ioDispatcher) {
            authRepository.register(email, name, password)
        }
    }
}
