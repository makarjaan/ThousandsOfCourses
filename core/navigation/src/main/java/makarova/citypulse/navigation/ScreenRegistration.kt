package makarova.citypulse.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface ScreenRegistration {
    fun registerScreens(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )
}