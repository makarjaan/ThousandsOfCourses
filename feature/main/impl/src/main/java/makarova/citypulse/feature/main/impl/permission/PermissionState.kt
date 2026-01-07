package makarova.citypulse.feature.main.impl.permission

import makarova.citypulse.feature.main.api.model.CityModel

sealed class DetectionResult {
    object PermissionRequired : DetectionResult()
    data class Success(val city: CityModel) : DetectionResult()
    data class Error(val message: String) : DetectionResult()
}