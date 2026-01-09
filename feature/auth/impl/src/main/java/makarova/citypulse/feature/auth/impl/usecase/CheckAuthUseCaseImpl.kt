package makarova.citypulse.feature.auth.impl.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import makarova.citypulse.database.local.JwtManager
import makarova.citypulse.database.local.TokenStorage
import makarova.citypulse.feature.auth.api.usecase.CheckAuthUseCase
import javax.inject.Inject

class CheckAuthUseCaseImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val jwtManager: JwtManager,
    private val ioDispatcher: CoroutineDispatcher
) : CheckAuthUseCase {

    override suspend fun invoke(): Boolean {
        return withContext(ioDispatcher) {
            val token = tokenStorage.getToken() ?: return@withContext false
            return@withContext jwtManager.isTokenValid(token)
        }
    }
}
