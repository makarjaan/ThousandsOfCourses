package makarova.citypulse.feature.profile.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import makarova.citypulse.feature.auth.api.navigation.LoginRoute
import makarova.citypulse.feature.favorite.api.navigation.FavoriteRoute
import makarova.citypulse.feature.profile.api.navigation.ProfileRoute
import makarova.citypulse.feature.profile.impl.ui.screen.ProfileScreen

fun NavGraphBuilder.profileNavGraph(
    navController: NavController
) {

    composable(ProfileRoute.destination) {
        ProfileScreen(
            onBack = { navController.popBackStack() },
            onFavorites = { navController.navigate(FavoriteRoute.destination)},
            onLogout = { navController.navigate(LoginRoute.destination)}
        )
    }

}