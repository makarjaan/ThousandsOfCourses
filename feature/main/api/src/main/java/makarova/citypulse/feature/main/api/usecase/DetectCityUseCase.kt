package makarova.citypulse.feature.main.api.usecase

import makarova.citypulse.feature.main.api.model.DetectCityResult


interface DetectCityUseCase {
    suspend fun invoke(): DetectCityResult?
}
