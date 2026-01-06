package makarova.citypulse.feature.auth.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import makarova.citypulse.feature.auth.api.navigation.LoginRoute
import makarova.citypulse.feature.auth.api.navigation.RegistrationRoute
import makarova.citypulse.feature.auth.impl.ui.screen.LoginScreen
import makarova.citypulse.feature.auth.impl.ui.screen.RegistrationScreen
import makarova.citypulse.utils.Routes

fun NavGraphBuilder.authNavGraph(
    navController: NavController
) {
    composable(LoginRoute.destination) {
        LoginScreen(
            onLoginSuccess = {
                navController.navigate(Routes.MAIN) {
                    popUpTo(LoginRoute.destination) { inclusive = true }
                }
            },
            onNavigateToRegistration = {
                navController.navigate(RegistrationRoute.destination)
            }
        )
    }

    composable(RegistrationRoute.destination) {
        RegistrationScreen(
            onRegistrationSuccess = {
                navController.navigate(Routes.MAIN) {
                    popUpTo(LoginRoute.destination) { inclusive = true }
                }
            },
            onNavigateToLogin = {
                navController.navigate(LoginRoute.destination)
            }
        )
    }
}
