package makarova.citypulse.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.profile.api.usecase.UpdateUserAvatarUseCase
import javax.inject.Inject

class UpdateUserAvatarUseCaseImpl @Inject constructor(
    private val repository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) : UpdateUserAvatarUseCase {
    
    override suspend fun invoke(email: String, avatarUrl: String?): Boolean {
        return withContext(ioDispatcher) {
            return@withContext repository.updateUserAvatar(email, avatarUrl)
        }
    }
}