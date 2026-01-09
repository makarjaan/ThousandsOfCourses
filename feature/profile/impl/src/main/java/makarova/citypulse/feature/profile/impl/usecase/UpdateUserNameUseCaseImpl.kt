package makarova.citypulse.feature.profile.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.profile.api.usecase.UpdateUserNameUseCase
import javax.inject.Inject

class UpdateUserNameUseCaseImpl @Inject constructor(
    private val repository: AuthRepository,
    private val ioDispatcher: CoroutineDispatcher
) : UpdateUserNameUseCase {
    
    override suspend fun invoke(email: String, newName: String): Boolean {
        return withContext(ioDispatcher) {
            return@withContext repository.updateUserName(email, newName)
        }
    }
}