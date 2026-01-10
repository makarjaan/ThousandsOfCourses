package makarova.citypulse.feature.auth.api.usecase

interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String): Boolean
}
