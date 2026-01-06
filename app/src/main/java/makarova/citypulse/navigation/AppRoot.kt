package makarova.citypulse.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import makarova.citypulse.feature.auth.api.navigation.LoginRoute
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import makarova.citypulse.navigation.AppViewModel

@Composable
fun AppRoot(
    viewModel: AppViewModel = hiltViewModel()
) {
    val isAuthorized by viewModel.isAuthorized.collectAsState()

    when (isAuthorized) {
        null -> SplashScreen()
        true -> SplashScreen()
        false -> AppNavHost(startDestination = LoginRoute.destination)
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

