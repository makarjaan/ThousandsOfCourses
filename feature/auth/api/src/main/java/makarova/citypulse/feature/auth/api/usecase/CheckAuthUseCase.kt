package makarova.citypulse.feature.auth.api.usecase

interface CheckAuthUseCase {
    suspend operator fun invoke(): Boolean
}