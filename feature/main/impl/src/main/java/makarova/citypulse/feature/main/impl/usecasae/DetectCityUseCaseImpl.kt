package makarova.citypulse.feature.main.impl.usecasae

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import makarova.citypulse.feature.main.api.usecase.DetectCityUseCase
import makarova.citypulse.feature.main.impl.utils.AvailableCities
import java.util.Locale
import javax.inject.Inject
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.suspendCancellableCoroutine
import makarova.citypulse.feature.main.api.model.DetectCityResult
import kotlin.coroutines.resume

class DetectCityUseCaseImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) : DetectCityUseCase {

    override suspend fun invoke(): DetectCityResult = withContext(ioDispatcher) {

        if (!hasLocationPermission()) {
            return@withContext DetectCityResult.PermissionRequired
        }

        val locationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            val location = getLastLocation(locationClient)

            if (location == null) {
                return@withContext DetectCityResult.NotFound
            }


            val geocoder = Geocoder(context, Locale.getDefault())

            val addresses = try {
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            } catch (e: Exception) {
                return@withContext DetectCityResult.Error("Ошибка геокодирования")
            }

            val cityName = addresses?.firstOrNull()?.locality
            if (cityName == null) {
                return@withContext DetectCityResult.NotFound
            }


            val foundCity = AvailableCities.firstOrNull {
                it.name.equals(cityName, ignoreCase = true)
            }

            if (foundCity == null) {
                return@withContext DetectCityResult.NotFound
            }

            return@withContext DetectCityResult.Success(foundCity)
        } catch (e: Exception) {
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

