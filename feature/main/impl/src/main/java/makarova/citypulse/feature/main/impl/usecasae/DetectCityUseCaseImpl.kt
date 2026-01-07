package makarova.citypulse.feature.main.impl.usecasae

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.main.api.usecase.DetectCityUseCase
import makarova.citypulse.feature.main.impl.utils.AvailableCities
import java.util.Locale
import javax.inject.Inject
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import makarova.citypulse.feature.main.api.model.DetectCityResult
import kotlin.coroutines.resume

class DetectCityUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DetectCityUseCase {

    companion object {
        private const val TAG = "TEST_TAG_DetectCityUseCase"
    }

    override suspend fun invoke(): DetectCityResult = withContext(Dispatchers.IO) {
        Log.d(TAG, "Запуск определения города")

        if (!hasLocationPermission()) {
            Log.w(TAG, "Нет разрешений на геолокацию")
            return@withContext DetectCityResult.PermissionRequired
        }

        Log.d(TAG, "Разрешения на геолокацию есть")

        val locationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            val location = getLastLocation(locationClient)

            if (location == null) {
                Log.w(TAG, "Не удалось получить локацию")
                return@withContext DetectCityResult.NotFound
            }

            Log.d(TAG, "Получена локация: lat=${location.latitude}, lon=${location.longitude}")

            val geocoder = Geocoder(context, Locale.getDefault())

            val addresses = try {
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка Geocoder: ${e.message}")
                return@withContext DetectCityResult.Error("Ошибка геокодирования")
            }

            Log.d(TAG, "Получено адресов: ${addresses?.size ?: 0}")

            val cityName = addresses?.firstOrNull()?.locality
            if (cityName == null) {
                Log.w(TAG, "Не удалось определить название города")
                return@withContext DetectCityResult.NotFound
            }

            Log.d(TAG, "Определен город: $cityName")

            val foundCity = AvailableCities.firstOrNull {
                it.name.equals(cityName, ignoreCase = true)
            }

            if (foundCity == null) {
                Log.w(TAG, "Город '$cityName' не найден в списке доступных городов")
                return@withContext DetectCityResult.NotFound
            }

            Log.d(TAG, "Найден город в базе: ${foundCity.name} (${foundCity.slug})")
            return@withContext DetectCityResult.Success(foundCity)
        } catch (e: Exception) {
            Log.e(TAG, "Общая ошибка: ${e.message}", e)
            return@withContext DetectCityResult.Error(e.message ?: "Неизвестная ошибка")
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private suspend fun getLastLocation(
        locationClient: FusedLocationProviderClient
    ): android.location.Location? = suspendCancellableCoroutine { continuation ->
        locationClient.lastLocation
            .addOnSuccessListener { location ->
                continuation.resume(location)
            }
            .addOnFailureListener { exception ->
                continuation.resume(null)
            }
            .addOnCanceledListener {
                continuation.resume(null)
            }
    }
}

