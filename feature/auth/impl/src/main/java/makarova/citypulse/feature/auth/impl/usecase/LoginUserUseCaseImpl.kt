package makarova.citypulse.feature.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.auth.api.model.UserLoginModel
import javax.inject.Inject

class LoginUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(user: UserLoginModel) {
        withContext(ioDispatcher) {
            authRepository.loginUser(userLoginModel = user)
        }
    }
}