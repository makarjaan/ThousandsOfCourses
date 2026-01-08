package makarova.citypulse.feature.detail.impl.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import makarova.citypulse.designsystem.ui.AppTheme
import makarova.citypulse.feature.detail.impl.ui.components.ProfileHeader
import makarova.citypulse.feature.detail.impl.ui.components.ProfileLogout
import makarova.citypulse.feature.detail.impl.ui.components.ProfileMenuItem

@Composable
fun ProfileScreen(
    userName: String = "Арина",
    cityName: String = "Москва",
    favoritesCount: Int = 12,
    onChangeAvatar: () -> Unit,
    onFavoritesClick: () -> Unit,
    onCategoriesClick: () -> Unit,
    onChangeCityClick: () -> Unit,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ProfileHeader(
            userName = userName,
            cityName = cityName,
            onBackClick = onBackClick,
            onAvatarClick = onChangeAvatar
        )

        Spacer(modifier = Modifier.height(24.dp))


            ProfileMenuItem(
                title = "Избранные события",
                subtitle = "$favoritesCount событий",
                icon = Icons.Default.FavoriteBorder,
                onClick = onFavoritesClick
            )

            ProfileMenuItem(
                title = "Категории интересов",
                subtitle = "Настрой рекомендации",
                icon = Icons.Default.Tune,
                onClick = onCategoriesClick
            )

            ProfileMenuItem(
                title = "Город",
                subtitle = cityName,
                icon = Icons.Default.LocationOn,
                onClick = onChangeCityClick
            )


        Spacer(modifier = Modifier.weight(1f))

        ProfileLogout(onClick = onLogoutClick)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            cityName = "Kazan",
            favoritesCount = 1,
            onChangeAvatar = {},
            onCategoriesClick = {},
            onChangeCityClick = {},
            userName = "Алексей Иванов",
            onFavoritesClick = {},
            onBackClick = {},
            onLogoutClick = {}
        )
    }
}

