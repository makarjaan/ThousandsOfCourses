package makarova.thousandsofcourses.feature.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.thousandsofcourses.feature.auth.api.repository.AuthRepository
import makarova.thousandsofcourses.feature.auth.api.model.UserLoginModel
import javax.inject.Inject

class LoginUserUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(user: UserLoginModel) {
        withContext(ioDispatcher) {
            if (!authRepository.userInDb()) {
                authRepository.loginUser(userLoginModel = user)
            }
        }
    }
}