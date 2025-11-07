package makarova.thousandsofcourses.feature.favorite.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import makarova.thousandsofcourses.feature.favorite.impl.ui.screen.FavoriteRoute
import makarova.thousandsofcourses.navigation.ScreenRegistration
import makarova.thousandsofcourses.utils.Routes

class FavoriteScreenRegistrationImpl: ScreenRegistration {

    override fun registerScreens(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(Routes.FAVORITES) {
            FavoriteRoute()
        }
    }
}