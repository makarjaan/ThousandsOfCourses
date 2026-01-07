package makarova.citypulse.feature.main.api.model

sealed class DetectCityResult {
    object PermissionRequired : DetectCityResult()
    object NotFound : DetectCityResult()
    data class Success(val city: CityModel) : DetectCityResult()
    data class Error(val message: String) : DetectCityResult()
}
