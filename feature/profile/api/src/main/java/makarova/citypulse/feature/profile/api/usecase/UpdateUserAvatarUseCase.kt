package makarova.citypulse.feature.profile.api.usecase

interface UpdateUserAvatarUseCase {
    suspend operator fun invoke(email: String, avatarUrl: String?): Boolean
}