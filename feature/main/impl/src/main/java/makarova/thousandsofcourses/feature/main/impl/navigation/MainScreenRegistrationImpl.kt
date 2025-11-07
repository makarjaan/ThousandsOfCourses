package makarova.thousandsofcourses.feature.main.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import makarova.thousandsofcourses.feature.main.impl.ui.screen.MainRoute
import makarova.thousandsofcourses.navigation.ScreenRegistration
import makarova.thousandsofcourses.utils.Routes

class MainScreenRegistrationImpl: ScreenRegistration {

    override fun registerScreens(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(Routes.MAIN) {
            MainRoute()
        }
    }
}