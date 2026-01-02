package makarova.citypulse.feature.auth.api.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.model.UserLoginModel
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(login: String, password: String) {
        return withContext(ioDispatcher) {
            authRepository.loginUser(UserLoginModel(login, password))
        }
    }
}