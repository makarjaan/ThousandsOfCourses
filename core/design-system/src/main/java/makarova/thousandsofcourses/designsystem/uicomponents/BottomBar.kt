package makarova.thousandsofcourses.designsystem.uicomponents

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import makarova.thousandsofcourses.designsystem.R
import makarova.thousandsofcourses.utils.Routes

sealed class BottomScreen(val route: String, val label: String, @DrawableRes val iconRes: Int) {
    object Main: BottomScreen(route = Routes.MAIN, label = "Главная", iconRes = R.drawable.ic_main)
    object Favorites: BottomScreen(route = Routes.FAVORITES, label = "Избранное", iconRes = R.drawable.ic_favorites)
    object Account: BottomScreen(route = Routes.ACCOUNT, label = "Аккаунт", iconRes = R.drawable.ic_account)
}

@Composable
fun BottomBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomScreen.Main,
        BottomScreen.Favorites,
        BottomScreen.Account
    )

    NavigationBar {
        items.forEach { screen ->
            val selected  = currentRoute == screen.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(screen.route) },
                icon = {
                    Icon(
                        painter = painterResource(screen.iconRes),
                        contentDescription = screen.route
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = Color.Transparent
                ),
                label = { Text(
                    text = screen.label
                )}
            )
        }
    }
}