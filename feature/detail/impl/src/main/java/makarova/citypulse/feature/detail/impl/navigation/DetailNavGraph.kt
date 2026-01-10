package makarova.citypulse.feature.detail.impl.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import makarova.citypulse.feature.detail.api.navigation.EventDetailRoute
import makarova.citypulse.feature.detail.impl.ui.screen.EventDetailScreen

fun NavGraphBuilder.detailNavGraph(
    navController: NavController
) {
    composable(
        route = "${EventDetailRoute.destination}/{eventId}",
        arguments = listOf(
            navArgument("eventId") { type = NavType.StringType}
        )
    ) { backStackEntry ->

        val eventId = backStackEntry.arguments?.getString("eventId") ?: ""

        EventDetailScreen(
            eventId = eventId,
            onBackClick = { navController.popBackStack() },
        )
    }
}
