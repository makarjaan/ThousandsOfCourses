package makarova.citypulse.feature.main.impl.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat

fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}

fun getLocationPermissions(): Array<String> {
    return arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
}

@Composable
fun rememberLocationPermissionLauncher(
    onGranted: () -> Unit,
    onDenied: () -> Unit = {}
): () -> Unit {
    var requestPermissions by remember { mutableStateOf<() -> Unit>({}) }
    
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.values.all { it }
        if (granted) {
            onGranted()
        } else {
            onDenied()
        }
    }
    
    requestPermissions = {
        launcher.launch(getLocationPermissions())
    }
    
    return requestPermissions
}