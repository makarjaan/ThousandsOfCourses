package makarova.thousandsofcourses.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    screenRegistration: Set<ScreenRegistration>
) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        screenRegistration.forEach { registration ->
            registration.registerScreens(this)
        }
    }
}