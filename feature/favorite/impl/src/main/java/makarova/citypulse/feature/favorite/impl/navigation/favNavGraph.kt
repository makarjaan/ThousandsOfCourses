package makarova.citypulse.feature.favorite.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import makarova.citypulse.feature.detail.api.navigation.EventDetailRoute
import makarova.citypulse.feature.favorite.api.navigation.FavoriteRoute
import makarova.citypulse.feature.favorite.impl.ui.screen.FavoriteScreen

fun NavGraphBuilder.favoriteNavGraph(
    navController: NavController
) {

    composable(FavoriteRoute.destination) {
        FavoriteScreen(
            onEventClick = { eventId ->
                navController.navigate("${EventDetailRoute.destination}/${eventId}")
            },
            onBackClick = { navController.popBackStack() },
        )
    }
}
