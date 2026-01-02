package makarova.citypulse.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import makarova.citypulse.designsystem.uicomponents.BottomBar
import makarova.citypulse.utils.Routes
import androidx.compose.runtime.getValue

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    screenRegistration: Set<ScreenRegistration>
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBottomBar = currentRoute != Routes.AUTH

    Scaffold(
        bottomBar = {
            if (showBottomBar) BottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.AUTH,
            modifier = Modifier.padding(padding)
        ) {
            screenRegistration.forEach { it.registerScreens(this, navController) }
        }
    }
}