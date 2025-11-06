package makarova.thousandsofcourses.feature.auth.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import makarova.thousandsofcourses.feature.auth.impl.ui.screen.AuthRoute
import makarova.thousandsofcourses.navigation.ScreenRegistration

class AuthScreenRegistrationImpl: ScreenRegistration {

    override fun registerScreens(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable("auth") {
            AuthRoute(
                onLoginSuccess = {
                    navController.navigate("main") 
                }
            )
        }
    }
}