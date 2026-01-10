package makarova.citypulse.feature.profile.api.usecase

interface ChangePasswordUseCase {
    suspend operator fun invoke(
        email: String,
        oldPassword: String,
        newPassword: String
    ): Boolean
}