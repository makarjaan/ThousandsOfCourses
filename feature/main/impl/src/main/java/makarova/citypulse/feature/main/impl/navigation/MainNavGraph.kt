package makarova.citypulse.feature.main.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import makarova.citypulse.feature.main.api.navigation.MainRoute
import makarova.citypulse.feature.main.impl.ui.screen.MainScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {

    composable(MainRoute.destination) {
        MainScreen()
    }

}