package makarova.citypulse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import makarova.citypulse.feature.auth.impl.navigation.authNavGraph
import makarova.citypulse.feature.detail.impl.navigation.detailNavGraph
import makarova.citypulse.feature.profile.impl.navigation.profileNavGraph
import makarova.citypulse.feature.main.impl.navigation.mainNavGraph

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
        mainNavGraph(navController)
        detailNavGraph(navController)
        profileNavGraph(navController)
    }
}
