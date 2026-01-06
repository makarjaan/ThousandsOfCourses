package makarova.citypulse.feature.auth.impl.usecase

import makarova.citypulse.database.local.JwtManager
import makarova.citypulse.database.local.TokenStorage
import makarova.citypulse.feature.auth.api.usecase.CheckAuthUseCase
import javax.inject.Inject

class CheckAuthUseCaseImpl @Inject constructor(
    private val tokenStorage: TokenStorage,
    private val jwtManager: JwtManager
) : CheckAuthUseCase {

    override suspend fun invoke(): Boolean {
        val token = tokenStorage.getToken() ?: return false
        return jwtManager.isTokenValid(token)
    }
}
