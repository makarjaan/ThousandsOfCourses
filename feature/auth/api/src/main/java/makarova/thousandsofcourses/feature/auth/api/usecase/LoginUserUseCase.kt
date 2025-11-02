package makarova.thousandsofcourses.feature.auth.api.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.thousandsofcourses.feature.auth.api.repository.AuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(login: String, password: String) {
        return withContext(ioDispatcher) {
            authRepository.loginUser(login = login, password = password)
        }
    }
}