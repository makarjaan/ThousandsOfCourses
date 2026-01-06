package makarova.citypulse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import makarova.citypulse.feature.auth.impl.navigation.authNavGraph

@Composable
fun AppNavHost(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authNavGraph(navController)
    }
}
