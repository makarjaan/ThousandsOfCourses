package makarova.citypulse.feature.auth.api.usecase

import makarova.citypulse.feature.auth.api.model.UserModel

interface GetCurrentUserUseCase {
    suspend operator fun invoke(): UserModel
}