package makarova.citypulse.feature.profile.api.usecase

interface UpdateUserNameUseCase {
    suspend operator fun invoke(email: String, newName: String): Boolean
}