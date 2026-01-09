package makarova.citypulse.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.profile.api.usecase.ChangePasswordUseCase
import javax.inject.Inject

class ChangePasswordUseCaseImpl @Inject constructor(
    private val repository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ChangePasswordUseCase {
    
    override suspend fun invoke(
        email: String,
        oldPassword: String,
        newPassword: String
    ): Boolean {
        return withContext(ioDispatcher) {
            return@withContext repository.changePassword(email, oldPassword, newPassword)
        }
    }
}