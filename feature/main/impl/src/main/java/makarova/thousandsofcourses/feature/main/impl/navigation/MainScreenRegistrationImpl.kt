package makarova.thousandsofcourses.feature.main.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import makarova.thousandsofcourses.feature.main.impl.ui.screen.MainRoute
import makarova.thousandsofcourses.navigation.ScreenRegistration

class MainScreenRegistrationImpl: ScreenRegistration {

    override fun registerScreens(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable("main") {
            MainRoute(
                onFilterChanged = {},
                onCourseLiked = {}
            )
        }
    }
}