package makarova.citypulse.feature.auth.api.usecase

interface RegisterUseCase {
    suspend operator fun invoke(email: String, name: String, password: String): Boolean
}